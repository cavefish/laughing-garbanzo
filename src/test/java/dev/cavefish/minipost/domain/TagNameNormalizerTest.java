package dev.cavefish.minipost.domain;

import dev.cavefish.minipost.domain.tags.TagNameNormalizer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TagNameNormalizerTest {

    @ParameterizedTest
    @CsvSource(delimiter = ':', value = {
            "tag:tag",
            "TAG:tag",
            "tAg:tag"
    })
    void testNormalize(String tagName, String tagNameNormalized) {
        // Arrange
        TagNameNormalizer normalizer = new TagNameNormalizer();
        // Act
        String result = normalizer.normalize(tagName);
        // Assert
        assertEquals(tagNameNormalized, result);
    }

    @Test
    void testNormalizeWithSpaces() {
        // Arrange
        TagNameNormalizer normalizer = new TagNameNormalizer();
        // Act
        String result = normalizer.normalize("   tAg    ");
        // Assert
        assertEquals("tag", result);
    }

    @Test
    void testNormalizeEmpty() {
        // Arrange
        TagNameNormalizer normalizer = new TagNameNormalizer();
        // Act
        String result = normalizer.normalize("       ");
        // Assert
        assertEquals("", result);
    }

    @Test
    void testNormalizeNull() {
        // Arrange
        TagNameNormalizer normalizer = new TagNameNormalizer();
        // Act
        String result = normalizer.normalize(null);
        // Assert
        assertEquals(null, result);
    }

}