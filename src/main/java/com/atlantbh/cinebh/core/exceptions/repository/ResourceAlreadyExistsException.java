package com.atlantbh.cinebh.core.exceptions.repository;

import com.atlantbh.cinebh.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends GeneralException {
    public ResourceAlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT.value(), message);
    }

    public ResourceAlreadyExistsException() {
        super(HttpStatus.CONFLICT.value());
    }
}