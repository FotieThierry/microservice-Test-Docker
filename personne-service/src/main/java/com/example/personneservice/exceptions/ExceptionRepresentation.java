package com.example.personneservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
public class ExceptionRepresentation {
    private HttpStatus status;
    private String errMessage;
}
