package com.senai.lab365.MiniProjeto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CrmValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCrm {
    String message() default "O CRM deve ser um número com exatamente 7 dígitos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}