package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    ClinicService clinicService;

    @InjectMocks
    VetController vetController;

    @Mock
    Map<String, Object> model;

    @BeforeEach
    void setUp() {
        //given
        Collection<Vet> vetCollection = List.of(new Vet());
        given(clinicService.findVets()).willReturn(vetCollection);
    }

    @Test
    void showVetList() {
        //given -- see setup()

        //when
        String viewName = vetController.showVetList(model);

        //then
        then(clinicService).should().findVets();
        then(model).should().put(anyString(), any(Vets.class));
        assertThat(viewName).isEqualTo("vets/vetList");
    }

    @Test
    void showResourcesVetList() {
        //given -- see setup()

        //when
        Vets actualVets = vetController.showResourcesVetList();

        //then
        then(clinicService).should().findVets();
        assertThat(actualVets.getVetList()).isNotEmpty();
    }
}