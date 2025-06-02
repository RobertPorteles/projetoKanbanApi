package br.com.kanbanapi.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "tarefa_kanban")
public class TarefaKanban {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    // Renomeado para "descricao" sem acento:
    @Column(name = "descricao", columnDefinition = "text")
    private String descricao;

    @Column(name = "responsavel", length = 100)
    private String responsavel;

    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @Column(name = "data_prevista_entrega")
    private Date dataPrevistaEntrega;

    @Column(name = "iniciado", nullable = false)
    private Boolean iniciado;

    @Column(name = "finalizado", nullable = false)
    private Boolean finalizado;
}

