package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import java.util.Date;
import java.util.List;

import br.com.iwstech.descontae.interfaces.web.dto.ConsumoUsuarioDTO;
import br.com.iwstech.descontae.interfaces.web.dto.DashboardChaveValorDTO;
import br.com.iwstech.descontae.interfaces.web.dto.DashboardSituacaoValorDTO;

public interface ConsumoCustomRepository {

    List<ConsumoUsuarioDTO> findByUsuario(Long idPessoa);
    Long findTotais(Date dataInicio, Date dataFim);
    Long findTotais(Long idCliente, Date dataInicio, Date dataFim);
    List<DashboardSituacaoValorDTO> findCartoesAtivos();
    List<DashboardSituacaoValorDTO> findCartoesAtivos(Long idCliente);
    List<DashboardChaveValorDTO> findTotaisByCidade(Date dataInicio, Date dataFim);
    List<DashboardChaveValorDTO> findTotaisByCidade(Long idCliente, Date dataInicio, Date dataFim);

    List<DashboardChaveValorDTO> findTotaisByBairro(Long idCidade, Date dataInicio, Date dataFim);
    List<DashboardChaveValorDTO> findTotaisByBairro(Long idCliente, Long idCidade, Date dataInicio, Date dataFim);
    List<DashboardChaveValorDTO> findTotaisByCategoria(Long idCidade, Date dataInicio, Date dataFim);
    List<DashboardChaveValorDTO> findTotaisByCategoria(Long idCliente, Long idCidade, Date dataInicio, Date dataFim);

}
