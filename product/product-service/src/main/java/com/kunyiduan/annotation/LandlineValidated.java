package com.kunyiduan.annotation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/10/09 09:36:00
 */
public class LandlineValidated implements ConstraintValidator<Landline, String> {

    private static final Pattern LANDLINE_PATTERN = Pattern.compile(
            "^\\d{4}-\\d{8}$"
    );

    @Override
    public void initialize(Landline constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        Matcher m = LANDLINE_PATTERN.matcher(value);
        return m.matches();
    }

}
