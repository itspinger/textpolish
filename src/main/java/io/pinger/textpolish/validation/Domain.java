package io.pinger.textpolish.validation;

import io.pinger.textpolish.validation.validator.DomainValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DomainValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Domain {

    String message() default "The requested domain does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
