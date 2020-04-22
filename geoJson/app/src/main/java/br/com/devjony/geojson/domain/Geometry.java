package br.com.devjony.geojson.domain;

import java.io.Serializable;
import java.util.List;

public class Geometry implements Serializable {
    private String type;
    private List<String> coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }
}
