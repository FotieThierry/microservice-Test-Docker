package com.example.personneservice.validators;

import com.example.personneservice.exceptions.ObjetValidatorsException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidators <T>{

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public void validate(T objectToValidate){
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
        if(!violations.isEmpty()){
            Set<String> errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ObjetValidatorsException(errorMessage, objectToValidate.getClass().getName());
        }
    }

}
