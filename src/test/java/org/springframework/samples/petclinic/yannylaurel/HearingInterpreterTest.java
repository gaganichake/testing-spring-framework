package org.springframework.samples.petclinic.yannylaurel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HearingInterpreterTest {

    HearingInterpreter hearingInterpreter;

    @Before
    public void setUp() throws Exception {
        hearingInterpreter = new HearingInterpreter(new LaurelWordProducer());
    }

    // A Simple JUnit 4 test
    @Test
    public void whatIHeardIsLaurel() {
        String word = hearingInterpreter.whatIHeard();
        assertEquals("Laurel", word);
    }
}