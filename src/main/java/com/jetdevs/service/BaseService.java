package com.jetdevs.service;

import com.jetdevs.exceptions.FileUploadException;
import com.jetdevs.model.User;
import com.jetdevs.utils.UserHelper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        User user = UserHelper.getUser(auth.getName());
        if (user == null) {
            throw new FileUploadException(HttpStatus.UNAUTHORIZED.value(), "Please login.!!");
        }
        return user;
    }

    String getAuthenticatedUserName() {
        return getAuthenticatedUser().getUserName();
    }


}
