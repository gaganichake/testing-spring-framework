package org.springframework.samples.petclinic.yannylaurel.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.yannylaurel.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("externalized")
@TestPropertySource("classpath:laurel.properties")
@SpringJUnitConfig(classes = HearingInterpreterLaurelPropertiesTest.Config.class)
class HearingInterpreterLaurelPropertiesTest {

    @Configuration
    @ComponentScan("org.springframework.samples.petclinic.yannylaurel")
    static class Config {
        // It will create Beans for all classes within the above package
    }

    // Spring will assign LaurelWordProducer as an implementation because it was annotated with @Primary
    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHeard() {
        String word = hearingInterpreter.whatIHeard();
        assertEquals("Laurel", word);
    }
}