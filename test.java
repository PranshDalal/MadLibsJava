package com.example.madlibs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MadLibsTest {
    @Test
    public void testReplacesAllPlaceholders() {
        String res = MadLibs.generate(Main.TEMPLATE, "beach", "found", "tiny", "shell", "gently", "surprised");
        assertFalse(res.contains("<place>"));
        assertFalse(res.contains("<verb>"));
        assertFalse(res.contains("<adjective>"));
        assertFalse(res.contains("<noun>"));
        assertFalse(res.contains("<adverb>"));
        assertFalse(res.contains("<emotion>"));

        assertTrue(res.contains("beach"));
        assertTrue(res.contains("found"));
        assertTrue(res.contains("tiny"));
        assertTrue(res.contains("shell"));
        assertTrue(res.contains("gently"));
        assertTrue(res.contains("surprised"));
    }

    @Test
    public void testTemplateNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            MadLibs.generate(null, "a", "b", "c", "d", "e", "f")
        );
    }

    @Test
    public void testMultipleOccurrences() {
        String template = "<noun> and <noun> went to the <place>.";
        String res = MadLibs.generate(template, "park", "run", "red", "cat", "fast", "glad");
        assertEquals("cat and cat went to the park.", res);
    }

    @Test
    public void testNullInputsHandled() {
        String res = MadLibs.generate("<place> <verb> <adjective>", null, "runs", null, null, null, null);
        assertFalse(res.contains("<place>"));
        assertFalse(res.contains("<adjective>"));
        assertTrue(res.contains("runs"));
    }
}
