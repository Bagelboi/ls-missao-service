package org.daniel.LS_missao_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;

    @Autowired
    public MissaoService(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }

    public List<Missao> findAll() {
        return missaoRepository.findAll();
    }

    public Optional<Missao> findById(Long id) {
        return missaoRepository.findById(id);
    }

    public Missao save(Missao missao) {
        return missaoRepository.save(missao);
    }

    public Missao update(Long id, Missao updatedMissao) {
        updatedMissao.setId(id);
        return missaoRepository.save(updatedMissao);
    }

    public void delete(Long id) {
        missaoRepository.deleteById(id);
    }
}

