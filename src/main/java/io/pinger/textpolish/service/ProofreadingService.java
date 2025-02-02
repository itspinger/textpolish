package io.pinger.textpolish.service;

import io.pinger.textpolish.dto.ProofreadResponseDto;
import io.pinger.textpolish.dto.TextPolishRequestDto;
import jakarta.validation.constraints.NotNull;

public interface ProofreadingService {

    /**
     * This method checks if the specified language exists in the language cache,
     * and returns false if not.
     *
     * @param language the language to check
     * @return whether this language exists in the cache
     */
    boolean existsLanguage(String language);

    /**
     * This method checks if the specified domain exists in the domain cache,
     * and returns false if not.
     *
     * @param domain the domain to check
     * @return whether this domain exists in the cache
     */
    boolean existsDomain(String domain);

    /**
     * This method proofreads the specified polish request, and returns the proofread content.
     * <p>
     * If the communication with the proofreading service fails, or an invalid response is
     * returned, an exception will be thrown and handled by spring and displayed instead.
     *
     * @param requestDto the validated polish request
     * @return the proofread content
     */
    @NotNull
    ProofreadResponseDto proofreadContent(TextPolishRequestDto requestDto);

}
