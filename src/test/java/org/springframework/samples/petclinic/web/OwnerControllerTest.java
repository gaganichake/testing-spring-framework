package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {

    @Autowired
    OwnerController ownerController;

    // ClinicService is an interface, hence Mock object was created in spring/mvc-test-config.xml
    @Autowired
    ClinicService clinicService;

    MockMvc mockMvc;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build(); //given
    }

    @AfterEach
    void tearDown() {
        reset(clinicService);
    }

    @Test
    void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new")) // when
                .andExpect(model().attributeExists("owner")) //then
                .andExpect(view().name("owners/createOrUpdateOwnerForm")) //then
                .andExpect(status().isOk()); //then
    }

    @Test
    void testProcessFindFormFindByNameNotFound() throws Exception {
        mockMvc.perform(get("/owners").param("lastName", "Do Not Find Me")) // when, given
                .andExpect(view().name("owners/findOwners")) // then
                .andExpect(status().isOk()); // then
    }

    // Two tests: Last Name as null and returning multiple Owners
    @Test
    void testProcessFindFormFindByNameFoundMany() throws Exception {

        Collection<Owner> ownersList = List.of(new Owner(), new Owner());

        given(clinicService.findOwnerByLastName("")).willReturn(ownersList);

        mockMvc.perform(get("/owners")) // when not passing Last Name then the Last Name will be null.
                .andExpect(model().attributeExists("selections")) //then
                .andExpect(view().name("owners/ownersList")) // then
                .andExpect(status().isOk()); // then

        // Argument Captor will capture the current value of the Last Name (which an empty String now).
        then(clinicService).should().findOwnerByLastName(stringArgumentCaptor.capture());

        // Assert that value Last Name of the Last Name is an empty String now (Using Argument Captor).
        assertThat(stringArgumentCaptor.getValue()).isEqualTo("");
    }

}