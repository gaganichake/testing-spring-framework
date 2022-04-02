package org.springframework.samples.petclinic.yannylaurel.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.yannylaurel.BaseConfig;
import org.springframework.samples.petclinic.yannylaurel.HearingInterpreter;
import org.springframework.samples.petclinic.yannylaurel.LaurelConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.Assert.assertEquals;

@SpringJUnitConfig(classes = {BaseConfig.class, LaurelConfig.class})
class HearingInterpreterLaurelTest {

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHeardIsLaurel() {
        String word = hearingInterpreter.whatIHeard();
        assertEquals("Laurel", word);
    }
}