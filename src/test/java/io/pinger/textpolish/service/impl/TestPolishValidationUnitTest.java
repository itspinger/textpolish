package io.pinger.textpolish.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.pinger.textpolish.dto.TextPolishRequestDto;
import io.pinger.textpolish.service.ProofreadingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestPolishValidationUnitTest {

    @Autowired
    private Validator validator;

    @MockitoBean
    private ProofreadingService proofreadingService;

    @Test
    void testValidator_whenNullLanguage_returnsError() {
        final TextPolishRequestDto requestDto = new TextPolishRequestDto();
        requestDto.setLanguage(null);
        requestDto.setDomain("business");
        requestDto.setContent("Hello");

        when(this.proofreadingService.existsDomain(any())).thenReturn(true);

        final BeanPropertyBindingResult result = new BeanPropertyBindingResult(requestDto, "test");
        this.validator.validate(requestDto, result);

        assertEquals(1, result.getFieldErrorCount());
        assertTrue(result.hasFieldErrors("language"));
        verify(this.proofreadingService, never()).existsLanguage(any());
    }

    @Test
    void testValidator_whenLanguageIsInvalid_returnsError() {
        // Arrange
        final TextPolishRequestDto requestDto = new TextPolishRequestDto();
        requestDto.setLanguage("en-Ul");
        requestDto.setDomain("business");
        requestDto.setContent("Hello");

        when(this.proofreadingService.existsDomain(any())).thenReturn(true);
        when(this.proofreadingService.existsLanguage(requestDto.getLanguage())).thenReturn(false);

        // Act
        final BeanPropertyBindingResult result = new BeanPropertyBindingResult(requestDto, "test");
        this.validator.validate(requestDto, result);

        // Assert
        assertEquals(1, result.getFieldErrorCount());
        assertTrue(result.hasFieldErrors("language"));
        verify(this.proofreadingService).existsLanguage(eq(requestDto.getLanguage()));
    }

    @Test
    void testValidator_whenDomainIsNull_returnsError() {
        final TextPolishRequestDto requestDto = new TextPolishRequestDto();
        requestDto.setLanguage("en-US");
        requestDto.setDomain(null);
        requestDto.setContent("Hello");

        when(this.proofreadingService.existsLanguage(requestDto.getLanguage())).thenReturn(true);

        final BeanPropertyBindingResult result = new BeanPropertyBindingResult(requestDto, "test");
        this.validator.validate(requestDto, result);

        assertEquals(1, result.getFieldErrorCount());
        assertTrue(result.hasFieldErrors("domain"));
        verify(this.proofreadingService, never()).existsDomain(any());
    }

    @Test
    void testValidator_whenDomainIsInvalid_returnsError() {
        final TextPolishRequestDto requestDto = new TextPolishRequestDto();
        requestDto.setLanguage("en-US");
        requestDto.setDomain("aa");
        requestDto.setContent("Hello");

        when(this.proofreadingService.existsLanguage(requestDto.getLanguage())).thenReturn(true);
        when(this.proofreadingService.existsDomain(any())).thenReturn(false);

        final BeanPropertyBindingResult result = new BeanPropertyBindingResult(requestDto, "test");
        this.validator.validate(requestDto, result);

        assertEquals(1, result.getFieldErrorCount());
        assertTrue(result.hasFieldErrors("domain"));
        verify(this.proofreadingService).existsDomain(eq(requestDto.getDomain()));
    }

    @Test
    void testValidator_whenContentIsNull_returnsError() {
        final TextPolishRequestDto requestDto = new TextPolishRequestDto();
        requestDto.setLanguage("en-US");
        requestDto.setDomain("business");
        requestDto.setContent(null);

        when(this.proofreadingService.existsLanguage(requestDto.getLanguage())).thenReturn(true);
        when(this.proofreadingService.existsDomain(requestDto.getDomain())).thenReturn(true);

        final BeanPropertyBindingResult result = new BeanPropertyBindingResult(requestDto, "test");
        this.validator.validate(requestDto, result);

        assertEquals(1, result.getFieldErrorCount());
        assertTrue(result.hasFieldErrors("content"));
        verify(this.proofreadingService).existsLanguage(any());
        verify(this.proofreadingService).existsLanguage(any());
    }

    @Test
    void testValidator_whenContentIsEmpty_returnsError() {
        final TextPolishRequestDto requestDto = new TextPolishRequestDto();
        requestDto.setLanguage("en-US");
        requestDto.setDomain("business");
        requestDto.setContent("");

        when(this.proofreadingService.existsLanguage(requestDto.getLanguage())).thenReturn(true);
        when(this.proofreadingService.existsDomain(requestDto.getDomain())).thenReturn(true);

        final BeanPropertyBindingResult result = new BeanPropertyBindingResult(requestDto, "test");
        this.validator.validate(requestDto, result);

        assertEquals(1, result.getFieldErrorCount());
        assertTrue(result.hasFieldErrors("content"));
        verify(this.proofreadingService).existsLanguage(any());
        verify(this.proofreadingService).existsLanguage(any());
    }

    @Test
    void testValidator_whenContentIsMoreThan30_returnsError() {
        final TextPolishRequestDto requestDto = new TextPolishRequestDto();
        requestDto.setLanguage("en-US");
        requestDto.setDomain("business");
        requestDto.setContent("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        when(this.proofreadingService.existsLanguage(requestDto.getLanguage())).thenReturn(true);
        when(this.proofreadingService.existsDomain(requestDto.getDomain())).thenReturn(true);

        final BeanPropertyBindingResult result = new BeanPropertyBindingResult(requestDto, "test");
        this.validator.validate(requestDto, result);

        assertEquals(1, result.getFieldErrorCount());
        assertTrue(result.hasFieldErrors("content"));
        verify(this.proofreadingService).existsLanguage(any());
        verify(this.proofreadingService).existsLanguage(any());
    }

    @Test
    void testValidator_whenContentIsGood() {
        final TextPolishRequestDto requestDto = new TextPolishRequestDto();
        requestDto.setLanguage("en-US");
        requestDto.setDomain("business");
        requestDto.setContent("aaaaaaaaaa");

        when(this.proofreadingService.existsLanguage(requestDto.getLanguage())).thenReturn(true);
        when(this.proofreadingService.existsDomain(requestDto.getDomain())).thenReturn(true);

        final BeanPropertyBindingResult result = new BeanPropertyBindingResult(requestDto, "test");
        this.validator.validate(requestDto, result);

        assertEquals(0, result.getFieldErrorCount());
        verify(this.proofreadingService).existsLanguage(any());
        verify(this.proofreadingService).existsLanguage(any());
    }
}
