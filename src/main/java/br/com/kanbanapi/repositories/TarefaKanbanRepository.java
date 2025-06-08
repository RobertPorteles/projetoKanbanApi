package br.com.kanbanapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanbanapi.entities.TarefaKanban;
@Repository
public interface TarefaKanbanRepository extends JpaRepository<TarefaKanban, UUID> {
	

}
