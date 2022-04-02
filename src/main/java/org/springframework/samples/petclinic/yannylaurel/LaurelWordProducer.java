package org.springframework.samples.petclinic.yannylaurel;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary //Spring will choose this class first over others of the same type (WordProducer)
public class LaurelWordProducer implements WordProducer {
    @Override
    public String getWord() {
        return "Laurel";
    }
}
