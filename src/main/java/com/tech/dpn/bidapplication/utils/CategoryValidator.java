package com.tech.dpn.bidapplication.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class CategoryValidator implements ConstraintValidator<CategoryConstraint, String> {
    @Override
    public void initialize(CategoryConstraint categoryConstraint) {
    }

    @Override
    public boolean isValid(String categoryConstraint, ConstraintValidatorContext cxt) {
        List<Category> cats = Arrays.asList(Category.values());
        for (Category cat : cats) {
            if (cat.toString().equals(categoryConstraint)) {
                return true;
            }
        }
        return false;
    }
}
