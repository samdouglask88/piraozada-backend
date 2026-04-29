package piraozada.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import piraozada.domain.entity.Custo;
import piraozada.domain.entity.Pedido;
import piraozada.dto.FechamentoDTO;
import piraozada.repository.CustoRepository;
import piraozada.repository.PedidoRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FechamentoService {

    private final PedidoRepository pedidoRepository;
    private final CustoRepository custoRepository;

    public FechamentoDTO gerarFechamento(BigDecimal valorInvestido) {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<Custo> custos = custoRepository.findAll();

        // faturamento — soma de todos os pedidos
        BigDecimal faturamento = pedidos.stream()
                .map(Pedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal custoTotal = custos.stream()
                .map(Custo::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal lucro = faturamento.subtract(custoTotal);


        BigDecimal roas = BigDecimal.ZERO;
        if (valorInvestido != null && valorInvestido.compareTo(BigDecimal.ZERO) > 0) {
            roas = faturamento.divide(valorInvestido, 2, RoundingMode.HALF_UP);
        }

        FechamentoDTO fechamento = new FechamentoDTO();
        fechamento.setValorInvestido(valorInvestido);
        fechamento.setFaturamento(faturamento);
        fechamento.setCustoTotal(custoTotal);
        fechamento.setLucro(lucro);
        fechamento.setRoas(roas);

        return fechamento;
    }
}