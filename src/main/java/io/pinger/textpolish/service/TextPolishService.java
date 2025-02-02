package io.pinger.textpolish.service;

import io.pinger.textpolish.dto.TextPolishRequestDto;
import io.pinger.textpolish.dto.TextPolishResponseDto;

public interface TextPolishService {

    /**
     * This method polishes the valid (as per controller) {@link TextPolishResponseDto}
     * and returns the polished text and the similarity factor calculated by a similarity
     * algorithm.
     *
     * @param textPolishRequestDto the valid polish request
     * @return the polished response (text and the similarity factor)
     */
    TextPolishResponseDto polishText(TextPolishRequestDto textPolishRequestDto);

}
