package io.pinger.textpolish.validation.validator;

import io.pinger.textpolish.service.ProofreadingService;
import io.pinger.textpolish.validation.Domain;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DomainValidator implements ConstraintValidator<Domain, String> {
    private final ProofreadingService proofreadingService;

    public DomainValidator(ProofreadingService proofreadingService) {
        this.proofreadingService = proofreadingService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }

        return this.proofreadingService.existsDomain(value);
    }
}
