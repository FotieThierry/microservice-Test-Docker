package com.example.personneservice.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public class ObjetValidatorsException extends RuntimeException {

    private final Set<String> violations;
    private final String violtionSource;
}
