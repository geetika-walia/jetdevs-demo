package com.jetdevs.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.jetdevs.constants.Constants;
import com.jetdevs.exceptions.FileUploadException;
import com.jetdevs.model.Book;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;

public class FileHelper {

    public static List<Book> excelToBooks(InputStream inputStream) {
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<Book> books = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Book book = new Book();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            book.setName(currentCell.getStringCellValue());
                            break;
                        case 1:
                            book.setAuthorName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            book.setPrice(currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                books.add(book);
            }
            workbook.close();
            return books;
        } catch (Exception e) {
            throw new FileUploadException(HttpStatus.BAD_REQUEST.value(), String.format(Constants.FILE_UPLOAD_FAILED, e.getCause()));
        }
    }

}
