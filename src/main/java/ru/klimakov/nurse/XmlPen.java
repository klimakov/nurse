package ru.klimakov.nurse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlPen implements Pen {

    private File file;

    public XmlPen(File file) {
        this.file = file;
    }

    public XmlPen(String fileName) {
        this(new File(fileName));
    }

    @Override
    public void write(Infirmary infirmary) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Infirmary.class, Inmate.class, Injection.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(infirmary, file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
