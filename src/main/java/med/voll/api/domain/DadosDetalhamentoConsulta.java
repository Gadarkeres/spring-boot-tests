package med.voll.api.domain;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
        Long id,
        Long idPaciente,
        Long idMedico,
        LocalDateTime data) {
}
