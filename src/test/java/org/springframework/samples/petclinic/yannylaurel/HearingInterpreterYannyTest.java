package org.springframework.samples.petclinic.yannylaurel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class) // This is Spring Framework runner, not Spring Boot
@ContextConfiguration(classes = {BaseConfig.class, YannyConfig.class})
public class HearingInterpreterYannyTest {

    // Using Spring context to Autowire the bean
    @Autowired
    HearingInterpreter hearingInterpreter;

    // A Simple JUnit 4 test
    @Test
    public void whatIHeardIsYanny() {
        String word = hearingInterpreter.whatIHeard();
        assertEquals("Yanny", word);
    }
}