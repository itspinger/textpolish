package io.pinger.textpolish.service.impl;

import io.pinger.textpolish.dto.TextPolishRequestDto;
import io.pinger.textpolish.service.TextPolishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TextPolishServiceIntegrationTest {

    @Autowired
    private TextPolishService textPolishService;

    @Test
    void integrationTest() {
        final TextPolishRequestDto requestDto = new TextPolishRequestDto();
        requestDto.setLanguage("en-US");
        requestDto.setDomain("business");
        requestDto.setContent("Your text here");
        this.textPolishService.polishText(requestDto);
    }

}
