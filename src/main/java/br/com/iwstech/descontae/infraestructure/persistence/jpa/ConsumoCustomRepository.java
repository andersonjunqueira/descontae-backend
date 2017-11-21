package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.iwstech.descontae.interfaces.web.dto.ConsumoListDTO;
import br.com.iwstech.descontae.interfaces.web.dto.ConsumoUsuarioDTO;
import br.com.iwstech.descontae.interfaces.web.dto.DashboardChaveValorDTO;
import br.com.iwstech.descontae.interfaces.web.dto.DashboardSituacaoValorDTO;

public interface ConsumoCustomRepository {

    List<ConsumoUsuarioDTO> findByUsuario(Long idPessoa);

    Long findCartoesTotais();
    Long findCartoesTotais(Long idCliente);
    List<DashboardSituacaoValorDTO> findCartoesAtivos();
    List<DashboardSituacaoValorDTO> findCartoesAtivos(Long idCliente);

    Long findConsumosTotais(Date dataInicio, Date dataFim);
    Long findConsumosTotais(Long idCliente, Date dataInicio, Date dataFim);
    List<DashboardChaveValorDTO> findConsumosTotaisByCategoria(Long idCidade, Date dataInicio, Date dataFim);
    List<DashboardChaveValorDTO> findConsumosTotaisByCategoria(Long idCliente, Long idCidade, Date dataInicio, Date dataFim);
    List<DashboardChaveValorDTO> findTotaisByCidade(Date dataInicio, Date dataFim);
    List<DashboardChaveValorDTO> findTotaisByCidade(Long idCliente, Date dataInicio, Date dataFim);
    List<DashboardChaveValorDTO> findTotaisByBairro(Long idCidade, Date dataInicio, Date dataFim);
    List<DashboardChaveValorDTO> findTotaisByBairro(Long idCliente, Long idCidade, Date dataInicio, Date dataFim);

    Page<ConsumoListDTO> findAll(Long idCliente, Long idCidade, Date dataInicio, Date dataFim, Pageable pageable);

}
