package io.pinger.textpolish.validation;

import io.pinger.textpolish.validation.validator.LanguageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LanguageValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Language {

    String message() default "The requested language does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
