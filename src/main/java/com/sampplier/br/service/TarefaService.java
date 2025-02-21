package com.sampplier.br.service;


import com.sampplier.br.model.Tarefa;
import com.sampplier.br.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private static final Logger logger = LogManager.getLogger(TarefaService.class);
    private final TarefaRepository tarefaRepository;

    public List<Tarefa> listarTarefas() {
        logger.info("Listando todas as tarefas");
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa salvarTarefa(Tarefa tarefa) {
        logger.info("Salvando  tarefa: {}", tarefa.getTitulo());
        return tarefaRepository.save(tarefa);
    }

    public void deletarTarefa(Long id) {
        logger.warn("Deletando tarefa com ID {}", id);
        tarefaRepository.deleteById(id);
    }

    public Tarefa concluirTarefa(Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isPresent()) {
            Tarefa t = tarefa.get();
            t.setConcluida(true);
            t.setConcluidaEm(java.time.LocalDateTime.now());
            return tarefaRepository.save(t);
        }
        return null;
    }
}
