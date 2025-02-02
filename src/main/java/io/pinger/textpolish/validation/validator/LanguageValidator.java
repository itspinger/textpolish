package io.pinger.textpolish.validation.validator;

import io.pinger.textpolish.service.ProofreadingService;
import io.pinger.textpolish.validation.Language;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LanguageValidator implements ConstraintValidator<Language, String> {
    private final ProofreadingService proofreadingService;

    public LanguageValidator(ProofreadingService proofreadingService) {
        this.proofreadingService = proofreadingService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return this.proofreadingService.existsLanguage(value);
    }
}
