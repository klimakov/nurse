package ru.klimakov.nurse;

import javax.xml.bind.annotation.XmlAttribute;

public class Injection {

    private String name;

    private String cure;

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getCure() {
        return cure;
    }

    @XmlAttribute
    public void setCure(String cure) {
        this.cure = cure;
    }
}
