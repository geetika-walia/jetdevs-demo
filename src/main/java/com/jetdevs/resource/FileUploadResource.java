package com.jetdevs.resource;

import java.io.IOException;
import com.jetdevs.exceptions.FileUploadException;
import com.jetdevs.model.UserFiles;
import com.jetdevs.model.dto.UserFilesDto;
import com.jetdevs.service.FileUploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileUploadResource {

    private final FileUploadService fileUploadService;

    @RequestMapping(method = RequestMethod.POST, path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UserFiles> uploadFiles(@RequestPart("file") MultipartFile file) throws IOException {
        if (file != null) {
            return ResponseEntity.ok(fileUploadService.readFile(file.getInputStream(), file.getOriginalFilename()));
        }
        throw new FileUploadException(HttpStatus.NOT_FOUND.value(), "File not found.");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/record/{id}")
    public ResponseEntity<UserFilesDto> uploadFiles(@PathVariable("id") long id) {
        return ResponseEntity.ok(fileUploadService.listRecords(id));
    }

}
