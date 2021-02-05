package com.example.org.barrage;

import com.example.org.barrage.domain.Barrage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BarrageTest {

    @Test
    void should_convert_to_json() {
        Barrage barrage = new Barrage();
        barrage.setAuthor("Micheal");
        barrage.setContent("I love football");
        barrage.setAvatar("http://www.avatar.com/123.jpg");
        assertEquals("{\"author\":\"Micheal\",\"content\":\"I love football\",\"avatar\":\"http://www.avatar.com/123.jpg\"}", barrage.toJson());
    }

    @Test
    void should_convert_to_json_when_avatar_is_null() {
        Barrage barrage = new Barrage();
        barrage.setAuthor("James");
        barrage.setContent("I love MVP");
        assertEquals("{\"author\":\"James\",\"content\":\"I love MVP\",\"avatar\":null}", barrage.toJson());
    }

    @Test
    void should_parse_json() {
        String json = "{\"author\":\"Honglai\",\"content\":\"I am empty\",\"avatar\":\"http://www.avatar.com/123.jpg\"}";
        Barrage barrage = Barrage.format(json);
        assertNotNull(barrage);
        assertEquals("Honglai", barrage.getAuthor());
        assertEquals("I am empty", barrage.getContent());
        assertEquals("http://www.avatar.com/123.jpg", barrage.getAvatar());
    }

    @Test
    void should_parse_json_when_avatar_is_null() {
        String json = "{\"author\":\"Durant\",\"content\":\"I love Movie Fans\",\"avatar\":null}";
        Barrage barrage = Barrage.format(json);
        assertNotNull(barrage);
        assertEquals("Durant", barrage.getAuthor());
        assertEquals("I love Movie Fans", barrage.getContent());
        assertNull(barrage.getAvatar());
    }
}
