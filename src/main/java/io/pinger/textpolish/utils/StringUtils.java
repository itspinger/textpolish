package io.pinger.textpolish.utils;

import java.util.regex.Pattern;

public class StringUtils {
    private static final Pattern TAG_PATTERN = Pattern.compile("<.*/>");

    /**
     * This method removes any enclosing tags in the provided string, and returns
     * the string without it. A tag is a text enclosed with < //>.
     * <p>
     * Suppose that we are calling <pre>{@code StringUtils.removeEnclosingTags("<mark />Hello")}</pre>
     * This method will remove the tags and return {@code Hello}
     * <p>
     * If the provided string has no enclosing tags, the exact same string will be returned.
     *
     * @param text the text which may have
     * @return the new string which has no tags
     */
    public static String removeEnclosingTags(String text) {
        return text.replaceAll(TAG_PATTERN.pattern(), "");
    }
}
