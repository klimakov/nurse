package ru.klimakov.nurse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Infirmary {

    private List<Inmate> inmates = new ArrayList<>();

    public List<Inmate> getInmates() {
        return inmates;
    }

    @XmlElement(name = "inmate")
    public void setInmates(List<Inmate> inmates) {
        this.inmates = inmates;
    }
}
