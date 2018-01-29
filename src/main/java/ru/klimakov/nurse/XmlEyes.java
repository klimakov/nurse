package ru.klimakov.nurse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.URL;

public class XmlEyes implements Eyes {

    private URL url;

    public XmlEyes(URL url) {
        this.url = url;
    }

    @Override
    public Infirmary read() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Infirmary.class, Inmate.class, Injection.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Infirmary infirmary = (Infirmary) unmarshaller.unmarshal(url);
            return infirmary;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
