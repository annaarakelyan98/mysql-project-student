package com.students.validation;

import com.students.model.Students;
import jakarta.validation.*;

import java.util.Set;

public class ValidationImpl implements Validations {
    @Override
    public boolean valid(Students students) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Students>> violations = validator.validate(students);

        if (!violations.isEmpty()) {
            violations.forEach(violation -> {
                        String message = violation.getMessage();
                        System.out.println(message);
                    }
            );
            return false;
        } else {
            return true;
        }
    }
}
