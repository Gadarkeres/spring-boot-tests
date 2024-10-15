package med.voll.api.controller;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsulta(

        Long idMedico,
        @NotNull Long idPaciente,

        @NotNull @Future LocalDateTime data

) {

}