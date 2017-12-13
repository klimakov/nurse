package ru.klimakov.nurse;

import org.junit.Assert;
import org.junit.Test;

public class NurseTest {

    @Test
    public void shouldBuildRegister() {
        Nurse nurse = new Nurse();
        Register register = nurse.register(new Patient())
                .register(new Glucose())
                .register(new Water())
                .build();

        Glucose glucose = register.get(Glucose.class).get();
        Patient patient = register.get(Patient.class).get();
        Assert.assertEquals(glucose, patient.getGlucose());

    }
}
