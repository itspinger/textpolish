package io.pinger.textpolish.controller;

import io.pinger.textpolish.dto.TextPolishRequestDto;
import io.pinger.textpolish.dto.TextPolishResponseDto;
import io.pinger.textpolish.service.TextPolishService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/polish")
public class TextPolishController {
    private final TextPolishService textPolishService;

    public TextPolishController(TextPolishService textPolishService) {
        this.textPolishService = textPolishService;
    }

    @PostMapping
    public ResponseEntity<TextPolishResponseDto> polishText(@RequestBody @Valid TextPolishRequestDto textPolishRequestDto) {
        return ResponseEntity.ok(this.textPolishService.polishText(textPolishRequestDto));
    }

}
