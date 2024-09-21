package org.daniel.LS_missao_service;

import org.daniel.LS_missao_service.PlanetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/planeta")
public class PlanetClientController {
    private final WebClient webClient;
    private final Environment env;
    @Autowired
    public PlanetClientController(Environment _env) {
        env = _env;
        webClient = WebClient.builder().baseUrl(env.getProperty("msr.planet.url") + "/msr/planeta").build();
    }


    @GetMapping
    public Mono<List<PlanetaDTO>> getAllPlanets() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(PlanetaDTO.class)
                .collectList();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PlanetaDTO>> getPlanetById(@PathVariable Long id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .toEntity(PlanetaDTO.class);
    }
}
