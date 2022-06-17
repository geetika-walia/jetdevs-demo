package com.jetdevs.model.dto;

import java.util.List;
import com.jetdevs.model.Book;
import com.jetdevs.model.UserFiles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class UserFilesDto extends UserFiles {

    private List<Book> books;

    private String uploadByName;

}
