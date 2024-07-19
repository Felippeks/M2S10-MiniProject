package com.senai.lab365.MiniProjeto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CrmValidator implements ConstraintValidator<ValidCrm, String> {
    @Override
    public boolean isValid(String crm, ConstraintValidatorContext context) {
        if (crm == null) {
            return false;
        }
        return crm.trim().matches("\\d{7}");
    }
}