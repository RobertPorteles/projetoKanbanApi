package br.com.kanbanapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TarefaKanbanResponseDto {

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 100, message = "O título não pode exceder 100 caracteres")
    private String titulo;

    @Size(max = 500, message = "A descrição não pode exceder 500 caracteres")
    private String descricao;

    @NotBlank(message = "O responsável é obrigatório")
    @Size(max = 30, message = "O nome do responsável não pode exceder 30 caracteres")
    private String responsavel;

    @NotNull(message = "A data prevista de entrega é obrigatória")
    @Pattern(
        regexp = "^\\d{4}-\\d{2}-\\d{2}$",
        message = "A data da tarefa deve estar no formato AAAA-MM-DD."
    )
    private String dataPrevistaEntrega;

    @NotNull(message = "O campo iniciado é obrigatório")
    private Boolean iniciado;

    @NotNull(message = "O campo finalizado é obrigatório")
    private Boolean finalizado;
}
