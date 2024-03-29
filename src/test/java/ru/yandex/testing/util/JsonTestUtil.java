package ru.yandex.testing.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author nemchinovrp
 */
public final class JsonTestUtil {

    private static final JsonParser PARSER = new JsonParser();

    private JsonTestUtil() {
    }

    public static void assertEquals(String expected, String actual) {
        JsonElement actualJson = parseJson(actual);
        JsonElement expectedJson = parseJson(expected);
        assertThat(actualJson, equalTo(expectedJson));
    }

    private static JsonElement parseJson(String json) {
        return PARSER.parse(json);
    }
}
