package io.pinger.textpolish.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    void removeEnclosingTags() {
        final String text = "<mark type=\"bold\" size=\"13\"/>Your text here1";
        final String result = StringUtils.removeEnclosingTags(text);
        assertEquals("Your text here1", result);
    }
}