package br.com.devjony.geojson.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import br.com.devjony.geojson.domain.Json;

public class JsonReaderWtriter {
    private String fileName, latitude, longitude, json;

    public JsonReaderWtriter(String fileName, String latitude, String longitude) {
        this.fileName = fileName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public JsonReaderWtriter(String json) {
        this.json = json;
    }

//    public String createJson() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(new Json());
//    }

    public Json readJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return (Json)objectMapper.readValue(json, Json.class);
    }
}
