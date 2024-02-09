package com.example.voitureservice.clients;

import com.example.voitureservice.modals.Personne;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@FeignClient(name = "PERSONNE-SERVICE")
public interface PersonneRestClient {
 static final String HOST = "/personnes";
    @GetMapping(HOST+"/{id}")
    @CircuitBreaker(name="personneService", fallbackMethod = "defaultGetPerssonneFail")
    Personne findPersonneById(@PathVariable Long id);

    default Personne defaultGetPerssonneFail(Long id, Exception exception){
        Personne personne = new Personne();
        personne.setId(id);
        personne.setNom("NOT AVAILABLE");
        personne.setPrenom("NOT AVAILABLE");
        return personne;
    }
}
