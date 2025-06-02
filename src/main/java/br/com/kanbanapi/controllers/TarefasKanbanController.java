package br.com.kanbanapi.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@Validated
public class TarefasKanbanController {

    @Autowired
    private TarefaKanbanRepository repository;

    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping
    public ResponseEntity<List<TarefaKanban>> listarTodas() {
        List<TarefaKanban> lista = repository.findAll();
        return ResponseEntity.ok(lista);
    }
    
 // Exemplo de conversão no controller sem uso de service:
    @PostMapping
    @Operation(
        summary = "Cadastro de tarefas Kanban",
        description = "Cria uma nova tarefa no sistema Kanban."
    )
    public ResponseEntity<TarefaKanbanResponseDto> post(
            @RequestBody @Valid TarefaKanbanRequestDto request) {

        // Mapeia automaticamente todos os campos do DTO para a entidade
        TarefaKanban tarefa = modelMapper.map(request, TarefaKanban.class);

        // Só precisamos definir campos que não vêm do cliente
        tarefa.setDataCriacao(new Date());

        // Persiste no banco
        TarefaKanban salva = repository.save(tarefa);

        // Converte entidade salva para DTO de resposta
        TarefaKanbanResponseDto response = modelMapper.map(salva, TarefaKanbanResponseDto.class);

        return ResponseEntity.ok(response);
    }



    @PutMapping("/{id}")
    public ResponseEntity<TarefaKanban> editarTarefa(
            @PathVariable UUID id,
            @Valid @RequestBody TarefaKanbanResponseDto dto) {

        Optional<TarefaKanban> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TarefaKanban existente = opt.get();
        modelMapper.map(dto, existente);
        TarefaKanban atualizada = repository.save(existente);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable UUID id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    
}
