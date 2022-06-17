package com.jetdevs.exceptions;

import javax.servlet.http.HttpServletRequest;
import com.jetdevs.resource.FileResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackageClasses = FileResource.class)
public class FileUploadExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {FileUploadException.class})
    @ResponseBody
    public ResponseEntity<ErrorMessage> handler(HttpServletRequest request, FileUploadException ex) {
        ErrorMessage message = new ErrorMessage(getStatus(request, ex.status), ex.getMessage());

        return ResponseEntity
                .status(message.getStatusCode())
                .body(message);
    }

    private int getStatus(HttpServletRequest request, int status) {
        if (status != 0) {
            return status;
        }
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        return statusCode;
    }

}
