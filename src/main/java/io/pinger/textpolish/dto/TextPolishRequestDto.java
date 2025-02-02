package io.pinger.textpolish.dto;

import io.pinger.textpolish.validation.Domain;
import io.pinger.textpolish.validation.Language;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextPolishRequestDto {

    @Language
    private String language;

    @Domain
    private String domain;

    @NotNull(message = "Content must not be null")
    @Size(min = 1, max = 30)
    private String content;

}
