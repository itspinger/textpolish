package io.pinger.textpolish.service.impl;

import io.pinger.textpolish.dto.ProofreadResponseDto;
import io.pinger.textpolish.dto.TextPolishRequestDto;
import io.pinger.textpolish.exception.InternalError;
import io.pinger.textpolish.exception.NotFoundException;
import io.pinger.textpolish.service.ProofreadingService;
import jakarta.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProofreadingServiceImpl implements ProofreadingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProofreadingServiceImpl.class);

    private final RestTemplate restTemplate;
    private final Set<String> languages = new HashSet<>();
    private final Set<String> domains = new HashSet<>();

    public ProofreadingServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean existsLanguage(String language) {
        return this.languages.contains(language);
    }

    @Override
    public boolean existsDomain(String domain) {
        return this.domains.contains(domain);
    }

    @Override
    public ProofreadResponseDto proofreadContent(TextPolishRequestDto requestDto) {
        final ResponseEntity<ProofreadResponseDto> responseEntity;
        try {
            responseEntity = this.restTemplate.postForEntity(
                "/proofread",
                requestDto,
                ProofreadResponseDto.class
            );
        } catch (Exception e) {
            LOGGER.error("Failed to communicate with proofreading service {0}", e);
            throw new InternalError();
        }

        final ProofreadResponseDto content = responseEntity.getBody();
        if (content == null) {
            throw new NotFoundException("Failed to retrieve content from request");
        }

        return content;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void buildCache() {
        this.fetchDomains();
        this.fetchLanguages();
    }

    @PostConstruct
    public void initializeCache() {
        this.buildCache();
    }

    private void fetchLanguages() {
        final ResponseEntity<List<String>> responseEntity;
        try {
            responseEntity = this.restTemplate.exchange(
                "/languages",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
            );
        } catch (Exception e) {
            LOGGER.error("Failed to communicate with proofreading service to read languages {0}", e);
            throw new InternalError();
        }

        final List<String> languages = responseEntity.getBody();
        if (languages == null) {
            throw new NotFoundException("Failed to find any languages");
        }

        this.languages.clear();
        this.languages.addAll(languages);
    }

    private void fetchDomains() {
        final ResponseEntity<List<String>> responseEntity;
        try {
            responseEntity = this.restTemplate.exchange(
                "/domains",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
            );
        } catch (Exception e) {
            LOGGER.error("Failed to communicate with proofreading service to read domains {0}", e);
            throw new InternalError();
        }

        final List<String> domains = responseEntity.getBody();
        if (domains == null) {
            throw new NotFoundException("Failed to find any languages");
        }

        this.domains.clear();
        this.domains.addAll(domains);
    }
}
