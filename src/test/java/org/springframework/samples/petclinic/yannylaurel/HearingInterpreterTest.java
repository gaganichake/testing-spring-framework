package org.springframework.samples.petclinic.yannylaurel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BaseConfig.class, LaurelConfig.class})
public class HearingInterpreterTest {

    // Using Spring context to Autowire the bean
    @Autowired
    HearingInterpreter hearingInterpreter;

    // A Simple JUnit 4 test
    @Test
    public void whatIHeardIsLaurel() {
        String word = hearingInterpreter.whatIHeard();
        assertEquals("Laurel", word);
    }
}