package org.daniel.LS_missao_service;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.containers.GenericContainer;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetContainerTest {

    public static GenericContainer<?> planetService = new GenericContainer<>("bagelboi/msrplanets:latest")
            .withExposedPorts(8082);

    private static WebClient webClient;

    @BeforeAll
    public static void setUp() {
        String baseUrl = "http://" + planetService.getHost() + ":8082" + "/msr/planeta";
        webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    @Test
    public void testGetAllPlanets() {
        Mono<List<PlanetaDTO>> planetListMono = webClient.get()
                .retrieve()
                .bodyToFlux(PlanetaDTO.class)
                .collectList();
    }

    @Test
    public void testGetPlanetById() {
        Long planetId = 1L; // Assuming a planet with ID 1 exists
        Mono<PlanetaDTO> planetMono = webClient.get()
                .uri("/{id}", planetId)
                .retrieve()
                .bodyToMono(PlanetaDTO.class);
    }
}
