package com.testing.system.web.parsers;


import com.testing.system.web.dto.CreateTestDto;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by yuri on 29.09.17.
 */
public class JsonParserTest {

    private String jsonString;

    @Before
    public void setUp() {
        JsonArrayBuilder firstOptionsArray = Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("text", "option1")
                        .add("isRight", true))
                .add(Json.createObjectBuilder()
                        .add("text", "option2")
                        .add("isRight", true));

        JsonArrayBuilder secondOptionsArray = Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("text", "option3")
                        .add("isRight", true))
                .add(Json.createObjectBuilder()
                        .add("text", "option4")
                        .add("isRight", true));

        JsonArrayBuilder questionsArray = Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("text", "question1")
                        .add("mark", 1)
                        .add("options", firstOptionsArray))
                .add(Json.createObjectBuilder()
                        .add("text", "question2")
                        .add("mark", 2)
                        .add("options", secondOptionsArray));



        JsonObjectBuilder testJsonObjectBuilder = Json.createObjectBuilder()
                .add("categoryId", 1)
                .add("title", "Test")
                .add("orderNumber", 1)
                .add("questions", questionsArray);


        try(Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(testJsonObjectBuilder.build());
            this.jsonString = writer.toString();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Test
    public void parse() {
        CreateTestDto createTestDto =
                JsonParser.parse(CreateTestDto.class, jsonString);

        System.out.println(createTestDto);

        assertEquals(createTestDto.getTitle(), "Test");
        assertEquals(createTestDto.getQuestions().get(0).getText(), "question1");
        assertEquals(createTestDto.getQuestions().get(0).getOptions().get(0).getText(), "option1");
    }
}
