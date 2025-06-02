package br.com.kanbanapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kanbanapi.entities.TarefaKanban;

public interface TarefaKanbanRepository extends JpaRepository<TarefaKanban, UUID> {
	

}
