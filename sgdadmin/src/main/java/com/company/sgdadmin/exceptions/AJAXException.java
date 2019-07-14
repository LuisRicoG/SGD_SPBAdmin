package com.company.sgdadmin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Qualtop
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Order")  // 404
public class AJAXException extends RuntimeException {

    public AJAXException(String message) {
        super(message);
    }
}
