package io.pinger.textpolish.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TextPolishResponseDto {

    @JsonProperty("polished_content")
    private String text;

    private double similarity;

}
