package com.jetdevs.service;

import java.time.LocalDateTime;
import java.util.List;
import com.jetdevs.exceptions.FileUploadException;
import com.jetdevs.model.UserFiles;
import com.jetdevs.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FileService extends BaseService {

    private FileRepository fileRepository;

    @Transactional
    public UserFiles save(final UserFiles files) {
        return fileRepository.save(files);
    }

    @Transactional(readOnly = true)
    public List<UserFiles> listAll() {
        return (List<UserFiles>) fileRepository.findAll();
    }

    @Transactional
    public void delete(long id) {
        UserFiles userFiles = getById(id);
        userFiles.setDeleted(Boolean.TRUE);
        userFiles.setUpdatedDate(LocalDateTime.now());
        save(userFiles);
    }

    @Transactional(readOnly = true)
    public UserFiles getById(Long id) {
        return fileRepository
                .findById(id)
                .orElseThrow(() -> new FileUploadException(HttpStatus.NOT_FOUND.value(), "File details not found."));
    }

}
