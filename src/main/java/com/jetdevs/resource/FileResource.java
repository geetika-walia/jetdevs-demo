package com.jetdevs.resource;

import java.util.List;
import com.jetdevs.model.UserFiles;
import com.jetdevs.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileResource {

    private final FileService fileService;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<UserFiles> getById(@PathVariable("id") long id) {
        return ResponseEntity.ok(fileService.getById(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public ResponseEntity<List<UserFiles>> listFiles() {
        return ResponseEntity.ok(fileService.listAll());
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        fileService.delete(id);
    }

}
