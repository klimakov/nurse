package ru.klimakov.nurse;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

public class XmlTest {


    @Test
    public void shouldSaveConfigurationToXmlFile() {
        Nurse nurse = new Nurse();
        nurse.scan("ru.klimakov");
        nurse.register(PatientWithInjectAnnotation.class);
        Register reg = nurse.build();
        Pen pen = new XmlPen("target/infirmary.xml");
        pen.write(reg.createInfirmary());
    }

    @Test
    public void shouldReadConfigurationFromXmlFile() {
        URL url = this.getClass().getResource("/infirmary.xml");
        Eyes eyes = new XmlEyes(url);
        Infirmary infirmary = eyes.read();
        Nurse nurse = new Nurse();
        Register reg = nurse.registerInfirmary(infirmary).build();
        SimplePatient patient = reg.get(SimplePatient.class).get();
        Assert.assertNotNull(patient);
        Assert.assertNotNull(patient.getGlucose());
    }
}
