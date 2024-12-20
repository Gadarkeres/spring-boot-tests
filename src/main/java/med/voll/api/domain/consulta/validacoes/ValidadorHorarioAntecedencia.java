package med.voll.api.domain.consulta.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorHorarioAntecedencia {

  public void validar(DadosAgendamentoConsulta dados) {
    var dataConsulta = dados.data();
    var agora = LocalDateTime.now();
    var diferenciaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

    if (diferenciaEmMinutos < 30) {
      throw new ValidacaoException("Consultas precisam ser agendadas pelo menos de 30 minutos");
    }
  }
}
