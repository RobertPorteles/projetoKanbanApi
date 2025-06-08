package br.com.kanbanapi.dtos;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TarefaKanbanResponseDto {

   private UUID id;
    private String titulo;
    private String descricao;
    private String responsavel;
    
    
    @NotNull(message = "A data prevista de entrega é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataPrevistaEntrega;

    private Boolean iniciado;
    private Boolean finalizado;
}

