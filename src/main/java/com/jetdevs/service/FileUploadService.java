package com.jetdevs.service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.jetdevs.constants.Constants;
import com.jetdevs.exceptions.FileUploadException;
import com.jetdevs.model.Book;
import com.jetdevs.model.UserFiles;
import com.jetdevs.model.dto.UserFilesDto;
import com.jetdevs.utils.FileHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class FileUploadService extends BaseService {

    private FileService fileService;
    private BookService bookService;

    @Transactional
    public UserFiles readFile(InputStream inputStream, String fileName) {
        if (!FilenameUtils.isExtension(fileName, new String[]{"xls", "xlsx"})) {
            throw new FileUploadException(HttpStatus.BAD_REQUEST.value(), Constants.FILE_FORMAT_NOT_ACCEPTED);
        }
        List<Book> books = FileHelper.excelToBooks(inputStream);

        if (CollectionUtils.isEmpty(books)) {
            throw new FileUploadException(HttpStatus.BAD_REQUEST.value(), Constants.FILE_EMPTY);
        }

        List<Book> validatedRecords = validateRecords(books);
        UserFiles file = fileService.save(prepareFileDetails(fileName, books.size(), validatedRecords.size()));

        bookService.saveAll(prepareBooks(validatedRecords, file));
        return file;
    }

    @Transactional
    public UserFilesDto listRecords(long id) {
        UserFiles byId = fileService.getById(id);
        byId.setUpdatedDate(LocalDateTime.now());
        fileService.save(byId);
        log.info("{} access by user {}", byId.getName(), getAuthenticatedUserName());
        return UserFilesDto
                .builder()
                .name(byId.getName())
                .totalRecords(byId.getTotalRecords())
                .totalUploaded(byId.getTotalUploaded())
                .uploadBy(byId.getUploadBy())
                .createdDate(byId.getCreatedDate())
                .updatedDate(byId.getUpdatedDate())
                .deleted(byId.getDeleted())
                .id(byId.getId()).uploadByName(getAuthenticatedUserName()).books(bookService.listAllByFile(id)).build();
    }

    private List<Book> validateRecords(List<Book> books) {
        return books
                .stream()
                .filter(book -> StringUtils.isNotBlank(book.getName()) && StringUtils.isNotBlank(book.getAuthorName()))
                .collect(Collectors.toList());
    }

    private List<Book> prepareBooks(List<Book> books, UserFiles file) {
        return books
                .stream()
                .map(book -> book
                        .toBuilder()
                        .file(file)
                        .build())
                .collect(Collectors.toList());
    }

    private UserFiles prepareFileDetails(String fileName, int totalFoundRecords, int totalUploadedRecords) {
        return new UserFiles()
                .toBuilder()
                .name(fileName)
                .totalRecords(totalFoundRecords)
                .totalUploaded(totalUploadedRecords)
                .uploadBy(getAuthenticatedUser().getId())
                .build();
    }

}
