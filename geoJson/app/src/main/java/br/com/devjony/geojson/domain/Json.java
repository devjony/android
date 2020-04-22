package br.com.devjony.geojson.domain;

import java.io.Serializable;
import java.util.List;

public class Json implements Serializable {

    private String type;
    private List<Features> features;

    public Json() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Features> getFeatures() {
        return features;
    }

    public void setFeatures(List<Features> features) {
        this.features = features;
    }
}
