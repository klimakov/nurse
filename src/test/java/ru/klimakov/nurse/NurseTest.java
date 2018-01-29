package ru.klimakov.nurse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NurseTest {

    Nurse nurse;

    @Before
    public void setUp() {
        this.nurse = new Nurse();
    }


    @Test
    public void shouldBuildRegister() {
        Register register = nurse.register(PatientWithInjectAnnotation.class)
                .register(new Glucose())
                .register(new Water())
                .build();

        Glucose glucose = register.get(Glucose.class).get();
        PatientWithInjectAnnotation patient = register.get(PatientWithInjectAnnotation.class).get();
        Assert.assertEquals(glucose, patient.getGlucose());
    }

    @Test
    public void shouldScanPackagesForCures() {

        nurse.scan("ru.klimakov.nurse");

        Register reg = nurse.build();
        Assert.assertTrue(reg.get(Glucose.class).isPresent());
    }

    @Test
    public void shouldScanPackagesRecursivelyForCures() {

        nurse.scan("ru.klimakov");

        Register reg = nurse.build();
        Assert.assertTrue(reg.get(Glucose.class).isPresent());
        Assert.assertTrue(reg.get(Water.class).isPresent());
        Assert.assertFalse((reg.get(PatientWithInjectAnnotation.class).isPresent()));
    }


}
