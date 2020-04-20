package br.com.devjony.jsonapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOError;
import java.io.IOException;

public class JsonReaderWriter {
    private String fileName, name, phone, json;

    public JsonReaderWriter(String name, String phone, String fileName) {
        this.fileName = fileName;
        this.name = name;
        this.phone = phone;
    }

    public JsonReaderWriter(String json) {
        this.json = json;
    }

    public String createJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(new Schedule(name, phone));
    }

    public Schedule readJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return (Schedule)objectMapper.readValue(json, Schedule.class);
    }

}
