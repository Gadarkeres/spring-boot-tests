package med.voll.api.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class AgendaDeConsultas {

  @Autowired
  private ConsultaRepository repository;

  @Autowired
  private MedicoRepository medicoRepository;

  @Autowired
  private PacienteRepository pacienteRepository;
  public void agendar(DadosAgendamentoConsulta dados){
    if(!pacienteRepository.existsById(dados.idPaciente())){
      throw new ValidacaoException("Paciente não encontrado");
    }

    if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
      throw new ValidacaoException("Médico não encontrado");
    }

    var paciente = pacienteRepository.findById(dados.idPaciente()).get();
    var medico = escolherMedico(dados);

    var consulta = new Consulta(null, medico, paciente, dados.data());


    repository.save(consulta);
  }
  private Medico escolherMedico(DadosAgendamentoConsulta dados) {
    if(dados.idMedico() != null){
      return medicoRepository.getReferenceById(dados.idMedico());
    }

    if(dados.especialidade() == null){
        throw new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido");
    }

    return medicoRepository.escolherMedicoAleatorioPorEspecialidadeLivreNaData(dados.especialidade(), dados.data());

  }
  
}
