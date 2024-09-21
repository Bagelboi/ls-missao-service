package org.daniel.LS_missao_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/missao")
public class MissaoController {

    private final MissaoService missaoService;

    @Autowired
    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @GetMapping
    public List<Missao> getAllMissoes() {
        return missaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Missao> getMissaoById(@PathVariable Long id) {
        Optional<Missao> missao = missaoService.findById(id);
        return missao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Missao> createMissao(@RequestBody Missao missao) {
        Missao savedMissao = missaoService.save(missao);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMissao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Missao> updateMissao(@PathVariable Long id, @RequestBody Missao updatedMissao) {
        if (!missaoService.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        updatedMissao.setId(id);
        Missao missao = missaoService.update(id, updatedMissao);
        return ResponseEntity.ok(missao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMissao(@PathVariable Long id) {
        if (missaoService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        missaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
