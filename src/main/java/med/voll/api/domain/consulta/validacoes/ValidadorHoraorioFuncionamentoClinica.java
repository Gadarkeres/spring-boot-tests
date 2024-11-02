package med.voll.api.domain.consulta.validacoes;

import java.time.DayOfWeek;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorHoraorioFuncionamentoClinica {
  public void validar(DadosAgendamentoConsulta dados) {
    var dataConsulta = dados.data();

    var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
    var depoisDaFechamentoDaClinica = dataConsulta.getHour() > 18;

    if (domingo || antesDaAberturaDaClinica || depoisDaFechamentoDaClinica) {
      throw new ValidacaoException("Consulta fora do horário de funcionamento da cliínica");

    }

  }
}
