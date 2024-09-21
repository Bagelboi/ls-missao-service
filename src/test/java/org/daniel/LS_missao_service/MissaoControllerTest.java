package org.daniel.LS_missao_service;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MissaoController.class)
@Slf4j
public class MissaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MissaoService missaoService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Missao missao;

    @BeforeEach
    void setup() throws Exception {
        missao = new Missao();
        missao.setId(1L);
        missao.setTitulo("Missao teste");
        missao.setDesc("Descricao");
        missaoService.save(missao);
        log.info(missaoService.findAll().toString());
    }

    @Test
    @Disabled
    public void getAllMissoes() throws Exception {
        mockMvc.perform(get("/missao"))
                .andExpect(status().isOk());
    }

    @Disabled
    @Test
    public void getMissaoById() throws Exception {
        mockMvc.perform(get("/missao/1"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/missao/2"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void createMissao() throws Exception{

        try {
            mockMvc.perform(post("/missao")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(missao)))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Disabled
    @Test
    public void updateMissao() throws Exception {

        mockMvc.perform(put("/missao/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(missao)))
                .andExpect(status().isOk());
    }
}