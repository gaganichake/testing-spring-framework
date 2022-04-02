package org.springframework.samples.petclinic.yannylaurel.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.yannylaurel.HearingInterpreter;
import org.springframework.samples.petclinic.yannylaurel.LaurelWordProducer;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = HearingInterpreterInnerClassTest.LaurelConfig.class)
class HearingInterpreterInnerClassTest {

    //Creating required bean within the class itself
    @Configuration
    static class LaurelConfig {
        @Bean
        HearingInterpreter hearingInterpreter() {
            return new HearingInterpreter(new LaurelWordProducer());
        }
    }

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHeardIsLaurel() {
        String word = hearingInterpreter.whatIHeard();
        assertEquals("Laurel", word);
    }
}