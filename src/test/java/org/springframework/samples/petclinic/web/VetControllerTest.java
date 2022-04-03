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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    ClinicService clinicService;

    @InjectMocks
    VetController vetController;

    @Mock
    Map<String, Object> model;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        //given
        Collection<Vet> vetCollection = List.of(new Vet());
        given(clinicService.findVets()).willReturn(vetCollection);

        //Spring Mock MVC Standalone Setup
        mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();
    }

    // Test using Spring Mock MVC
    @Test
    void testControllerShowVetList() throws Exception {
        mockMvc.perform(get("/vets.html")) // perform a GET request to URI "/vets.html".
                .andExpect(status().isOk()) // Expect an HTTP response code 200 (OK)
                .andExpect(model().attributeExists("vets")) // Expect the model to have an attribute "vets"
                .andExpect(view().name("vets/vetList")); // Expect view name "vets/vetList" in retur
        // Note, Spring MVC Test will also pass a real model object automatically, not the Mock model.
        // However, it is still using the Mock ClinicService
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