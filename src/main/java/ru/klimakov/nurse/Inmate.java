package ru.klimakov.nurse;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class Inmate {

    private String name;

    private List<Injection> injections = new ArrayList<>();

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public List<Injection> getInjections() {
        return injections;
    }

    @XmlElement(name = "injection")
    public void setInjections(List<Injection> injections) {
        this.injections = injections;
    }
}
