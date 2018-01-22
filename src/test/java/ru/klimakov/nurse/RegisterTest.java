package ru.klimakov.nurse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class RegisterTest {

    private Register register;

    @Before
    public void setup() {
        register = new Register();
    }

    @Test
    public void componentCanBeAddedAndRetrieved() {
        Object one = new Object();
        Object two = new Object();
        register.add("one", one);
        register.add("two", two);
        Object retrievedOne = register.get("one").get();
        Object retrievedTwo = register.get("two").get();
        Assert.assertEquals(retrievedOne, one);
        Assert.assertEquals(retrievedTwo, two);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotBeTwoComponentsWithSameName() {
        register.add("one", new Object());
        register.add("one", new Object());
    }

    @Test
    public void componentCanBeAddedAndRetrievedByType() {
        Glucose one = new Glucose();
        Water two = new Water();
        register.add(one);
        register.add(two);
        Water retrievedTwo = register.get(Water.class).get();
        Glucose retrievedOne = register.get(Glucose.class).get();
        Assert.assertEquals(retrievedOne, one);
        Assert.assertEquals(retrievedTwo, two);
    }

    @Test
    public void componentsCanBeInjectedInsideOtherComponents() {
        Glucose one = new Glucose();
        Patient patient = new Patient();
        register.add(one);
        register.add(patient);
        register.inject();
        Patient retrievedPatient = register.get(Patient.class).get();
        Glucose injectedOne = retrievedPatient.getGlucose();
        Assert.assertEquals(one, injectedOne);
    }

    @Test()
    public void getNotRegisteredThrowsException() {
        Optional<Object> optional = register.get("not registered");
        Assert.assertFalse(optional.isPresent());
    }

    @Test(expected = RuntimeException.class)
    public void notInstantiableThrowsException() {
        register.add(NotInstantiableClass.class);
    }

    class NotInstantiableClass{
        private NotInstantiableClass(){};
    }

}