package io.pinger.textpolish.configuration;

import org.apache.commons.text.similarity.CosineDistance;
import org.apache.commons.text.similarity.EditDistance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfiguration {

    @Bean
    public EditDistance<Double> editDistance() {
        return new CosineDistance();
    }

}
