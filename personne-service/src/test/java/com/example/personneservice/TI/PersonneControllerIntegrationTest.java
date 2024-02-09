package com.example.personneservice.TI;

import com.example.personneservice.Dto.PersonneDto;
import com.example.personneservice.controller.PersonneController;
import com.example.personneservice.entities.Personne;
import com.example.personneservice.mapper.PersonneMapperImpl;
import com.example.personneservice.repository.PersonneRepository;
import com.example.personneservice.testContainerAbstractClass.AbstractContainerBaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


@AutoConfigureMockMvc
@SpringBootTest
// @Testcontainers disign patter singleton
public class PersonneControllerIntegrationTest extends AbstractContainerBaseTest {

  /*  @Container
    private static MariaDBContainer mariaDBContainer = new MariaDBContainer(DockerImageName.parse("mariadb:latest"));
  */
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private Personne personne1;
    private Personne personne2;
    private PersonneDto personneDto1;
    private PersonneDto personneDto2;

    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private PersonneMapperImpl personneMapper;

    @BeforeEach
    public void setUp(){

        personneDto1 = PersonneDto.builder().id(1L).prenom("thierry").build();
        personneDto2 = PersonneDto.builder().id(2L).prenom("fotie").build();

        personne1 = personneMapper.dtoToPersonne(personneDto1);
        personne2 = personneMapper.dtoToPersonne(personneDto2);

        personne1.setDate(12);
        personne2.setDate(20);

        personneRepository.saveAll(List.of(personne1, personne2));
    }

    @Test
    public void savePersonneTest() throws Exception{

        ResultActions reponse = mockMvc.perform(post("/personnes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personneDto1)));

        reponse.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom").value("thierry"));

    }

    @Test
    public void getAgeTest() throws Exception {
        ResultActions response = mockMvc.perform(get("/personnes/age/average")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(Math.toIntExact(16)));
    }

    @Test
    public void getAllPersonnesTest() throws Exception{
        ResultActions response = mockMvc.perform(get("/personnes/all")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));
    }

    @Test
    public void updatePersonne() throws Exception{
        String correctKey = "La force n'est pas avec toi !";
        String badKey = "bad key";
        Long personne1Id = personne1.getId();
        Long personne2Id = personne2.getId();

        ResultActions reponseBadAge = mockMvc.perform(put("/personnes/update/{id}", personne1Id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("key", correctKey)
                .header("Accept-Language", "fr")
                .content(objectMapper.writeValueAsString(personne1)));

        ResultActions reponseBadKey = mockMvc.perform(put("/personnes/update/{id}", personne2Id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("key", badKey)
                .header("Accept-Language", "fr")
                .content(objectMapper.writeValueAsString(personne1)));

        ResultActions reponseCorrect = mockMvc.perform(put("/personnes/update/{id}", personne2Id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("key", correctKey)
                .header("Accept-Language", "fr")
                .content(objectMapper.writeValueAsString(personne1)));

        reponseBadAge.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("vous avez moin de 18 ans"));

        reponseBadKey.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("La force n'est pas avec toi !"));

        reponseCorrect.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("la force est avec toi"));


    }

}
