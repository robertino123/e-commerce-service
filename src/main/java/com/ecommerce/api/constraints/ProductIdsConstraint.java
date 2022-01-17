package com.ecommerce.api.constraints;

import com.ecommerce.api.constraints.validator.ProductIdsConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProductIdsConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductIdsConstraint {
    String message() default "Invalid id provided.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
