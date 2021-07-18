package com.kunyiduan.annotation.password;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidated implements ConstraintValidator<Password, String> {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "[.*[A-Z]+.*[a-z]+.*\\d+.*]{8,}|[.*[A-Z]+.*[0-9]+.*[a-z]+.*]{8,}|[.*[a-z]+.*[0-9]+.*[A-Z]+.*]{8,}|" +
                    "[.*[a-z]+.*[A-Z]+.*[0-9]+.*]{8,}|[.*[0-9]+.*[A-Z]+.*[a-z]+.*]{8,}|[.*[0-9]+.*[a-z]+.*[A-Z]+.*]{8,}"
    );

    @Override
    public void initialize(Password constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        Matcher m = PASSWORD_PATTERN.matcher(value);
        return m.matches();
    }
}
