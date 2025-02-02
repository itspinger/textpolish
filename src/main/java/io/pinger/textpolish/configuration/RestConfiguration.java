package io.pinger.textpolish.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestConfiguration {

    // This value should be configured in the applications.properties
    // It's the URL of the Proofreader Service API
    @Value(value = "${proofreader.api.url}")
    private String proofReaderApi;

    @Bean
    public RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(this.proofReaderApi));
        return restTemplate;
    }

}
