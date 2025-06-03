package br.com.kanbanapi.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kanbanapi.dtos.TarefaKanbanRequestDto;
import br.com.kanbanapi.dtos.TarefaKanbanResponseDto;
import br.com.kanbanapi.entities.TarefaKanban;
import br.com.kanbanapi.repositories.TarefaKanbanRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/kanban/tarefas")
public class TarefasKanbanController {

    @Autowired
    private TarefaKanbanRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tarefa por ID", description = "Retorna uma tarefa específica.")
    public TarefaKanbanResponseDto buscarPorId(@PathVariable UUID id) {
        var tarefa = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        return modelMapper.map(tarefa, TarefaKanbanResponseDto.class);
    }

    @GetMapping
    @Operation(summary = "Listar todas as tarefas", description = "Retorna uma lista com todas as tarefas.")
    public List<TarefaKanbanResponseDto> listarTodas() {
        return repository.findAll()
                .stream()
                .map(tarefa -> modelMapper.map(tarefa, TarefaKanbanResponseDto.class))
                .toList();
    }



    @PostMapping
    public TarefaKanbanResponseDto criarTarefa(@RequestBody @Valid TarefaKanbanRequestDto dto) {
        var entidade = modelMapper.map(dto, TarefaKanban.class);
        entidade.setDataCriacao(new Date());
        var salva = repository.save(entidade);
        return modelMapper.map(salva, TarefaKanbanResponseDto.class);
    }

    @PutMapping("/{id}")
    public TarefaKanbanResponseDto atualizarTarefa(@PathVariable UUID id, @RequestBody @Valid TarefaKanbanRequestDto dto) {
        var entidade = repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));
        modelMapper.map(dto, entidade); // sobrescreve campos existentes
        var atualizada = repository.save(entidade);
        return modelMapper.map(atualizada, TarefaKanbanResponseDto.class);
    }

    @DeleteMapping("/{id}")
    public String deletarTarefa(@PathVariable UUID id) {
        var entidade = repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));
        repository.delete(entidade);
        return "Tarefa excluída com sucesso.";
    }
}

