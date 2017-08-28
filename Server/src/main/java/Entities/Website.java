package Entities;

import java.io.Serializable;

public class Website implements Serializable {
    private String name;
    private String url;
    private CriminalClassification classifictclassification;

    public Website(String name, String url, CriminalClassification classification) {
        this.name = name;
        this.url = url;
        this.classifictclassification = classification;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public CriminalClassification getClassifictclassification() {
        return classifictclassification;
    }
}
