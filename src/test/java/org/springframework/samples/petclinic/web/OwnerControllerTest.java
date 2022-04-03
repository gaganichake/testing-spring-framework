package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {

    @Autowired
    OwnerController ownerController;

    // ClinicService is an interface, hence Mock object was created in spring/mvc-test-config.xml
    @Autowired
    ClinicService clinicService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build(); //given
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

}