package br.com.kanbanapi.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TarefaKanbanRequestDto {

    @NotBlank(message = "O título é obrigatório")
    @Size(min =8, max = 50, message = "O título não pode exceder 50 e tem que ser maior que 8 caracteres")
    private String titulo;

    @Size(min= 8, max = 150, message = "A descrição não pode exceder 150 e tem que ser maior que 8 caracteres")
    private String descricao;

    @NotBlank(message = "O responsável é obrigatório")
    @Size(min =8, max = 25, message = "O nome do responsável não pode exceder 25 e tem que ser maior que 8 caracteres")
    private String responsavel;

    @NotNull(message = "A data prevista de entrega é obrigatória")
    private Date dataPrevistaEntrega;

    @NotNull(message = "O campo iniciado é obrigatório")
    private Boolean iniciado;

    @NotNull(message = "O campo finalizado é obrigatório")
    private Boolean finalizado;
    
}
