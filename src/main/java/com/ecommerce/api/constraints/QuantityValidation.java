package com.ecommerce.api.constraints;

import com.ecommerce.api.constraints.validator.QuantityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = QuantityValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QuantityValidation {
    String message() default "This product is out of stock.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
