package org.springframework.samples.petclinic.yannylaurel;

import org.springframework.stereotype.Component;

@Component
public class LaurelWordProducer implements WordProducer {
    @Override
    public String getWord() {
        return "Laurel";
    }
}
