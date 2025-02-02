package io.pinger.textpolish.service.impl;

import io.pinger.textpolish.dto.ProofreadResponseDto;
import io.pinger.textpolish.dto.TextPolishRequestDto;
import io.pinger.textpolish.dto.TextPolishResponseDto;
import io.pinger.textpolish.service.ProofreadingService;
import io.pinger.textpolish.service.TextPolishService;
import io.pinger.textpolish.utils.StringUtils;
import org.apache.commons.text.similarity.EditDistance;
import org.springframework.stereotype.Service;

@Service
public class TextPolishServiceImpl implements TextPolishService {
    private final EditDistance<Double> editDistance;
    private final ProofreadingService proofreadingService;

    public TextPolishServiceImpl(EditDistance<Double> editDistance, ProofreadingService proofreadingService) {
        this.editDistance = editDistance;
        this.proofreadingService = proofreadingService;
    }

    @Override
    public TextPolishResponseDto polishText(TextPolishRequestDto request) {
        final ProofreadResponseDto content = this.proofreadingService.proofreadContent(request);
        final String text = content.getContent();

        final double similarity = this.editDistance.apply(
            StringUtils.removeEnclosingTags(request.getContent()),
            StringUtils.removeEnclosingTags(text)
        );

        final TextPolishResponseDto response = new TextPolishResponseDto();
        response.setText(text);
        response.setSimilarity(similarity);
        return response;
    }
}
