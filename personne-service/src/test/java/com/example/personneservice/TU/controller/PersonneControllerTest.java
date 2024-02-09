package com.example.personneservice.TU.controller;

import com.example.personneservice.Dto.PersonneDto;
import com.example.personneservice.controller.PersonneController;
import com.example.personneservice.entities.Personne;
import com.example.personneservice.serviceImpl.PersonneServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonneController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PersonneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonneServiceImpl personneService;

    @Autowired
    private ObjectMapper objectMapper;

    private PersonneDto personneDto1;
    private PersonneDto personneDto2;
    private Personne personne;
    private List<PersonneDto> personneDtoList;

    @BeforeEach
    public void init(){
        personneDto1 = PersonneDto.builder()
                .id(1L)
                .prenom("thierry")
                .build();

        personneDto2 = PersonneDto.builder()
                .id(2L)
                .prenom("sergio")
                .build();
        personne = Personne.builder()
                .id(1L)
                .prenom("thierry")
                .build();

        personneDtoList = List.of(personneDto1, personneDto2);
    }

    @Test
    public void savePersonneTest() throws Exception {
        BDDMockito.given(personneService.savePersonne(ArgumentMatchers.any())).willReturn(personne);

        ResultActions reponse = mockMvc.perform(post("/personnes")
                .contentType(MediaType.APPLICATION_JSON )
                .content(objectMapper.writeValueAsString(personne)));

        reponse.andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom", CoreMatchers.is(personne.getPrenom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(Math.toIntExact(personne.getId()))))
                ;

    }

    @Test
    public void getAgeTest() throws Exception {
        Double age = 12.0;
        BDDMockito.given(personneService.getAverageAge()).willReturn(age);

        ResultActions response = mockMvc.perform(get("/personnes/age/average")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is(age)));

    }

    @Test
    public  void getAllPersonnesTest() throws Exception {
        BDDMockito.given(personneService.getAllPersonnes()).willReturn(personneDtoList);

        ResultActions response = mockMvc.perform(get("/personnes/all")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(personneDtoList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].prenom", CoreMatchers.is(personneDtoList.get(0).getPrenom())));

    }

    @Test
    public void getPersonneByNameTest() throws Exception {
        BDDMockito.given(personneService.getPersonneByPrenom("thierry")).willReturn(personneDto1);

        ResultActions response = mockMvc.perform(get("/personnes")
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("last_name", "thierry"));

        response.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom", CoreMatchers.is(personneDto1.getPrenom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(Math.toIntExact(personneDto1.getId()))));


    }

    @Test
    public void updatePersonneTest() throws Exception {
        Long id = personneDto1.getId();
        String correctKey = "La force n'est pas avec toi !";
        String badKey = "this is another key";
        Personne personne = Personne.builder()
                .nom("fotie")
                .build();

        BDDMockito.given(personneService.getPersonneByKeyAndIdAndPaylod(id, correctKey, personne)).willReturn("SUCCES");
        BDDMockito.given(personneService.getPersonneByKeyAndIdAndPaylod(id, badKey, personne)).willThrow(new RuntimeException("ERROR"));

        ResultActions correctResponse = mockMvc.perform(put("/personnes/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("key", correctKey)
                .header("Accept-Language", "fr")
                .content(objectMapper.writeValueAsString(personne)));

        ResultActions badResponse = mockMvc.perform(put("/personnes/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("key", badKey)
                .header("Accept-Language", "fr")
                .content(objectMapper.writeValueAsString(personne)));

        correctResponse.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is("SUCCES")));

        badResponse.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is("ERROR")));


    }


}
