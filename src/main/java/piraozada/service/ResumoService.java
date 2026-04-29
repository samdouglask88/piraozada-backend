package piraozada.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import piraozada.domain.entity.Custo;
import piraozada.domain.entity.Pedido;
import piraozada.dto.ResumoDTO;
import piraozada.repository.CustoRepository;
import piraozada.repository.PedidoRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumoService {

    private final PedidoRepository pedidoRepository;
    private final CustoRepository custoRepository;

    public ResumoDTO gerarResumo() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<Custo> custos = custoRepository.findAll();

        ResumoDTO resumo = new ResumoDTO();


        BigDecimal faturamento = pedidos.stream()
                .map(Pedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        resumo.setFaturamento(faturamento);


        BigDecimal custoTotal = custos.stream()
                .map(Custo::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        resumo.setCustoTotal(custoTotal);


        resumo.setLucro(faturamento.subtract(custoTotal));


        if (!pedidos.isEmpty()) {
            BigDecimal ticketMedio = faturamento.divide(
                    new BigDecimal(pedidos.size()), 2, RoundingMode.HALF_UP);
            resumo.setTicketMedio(ticketMedio);
        } else {
            resumo.setTicketMedio(BigDecimal.ZERO);
        }


        resumo.setSaborMaisVendido(pedidos.stream()
                .collect(Collectors.groupingBy(p -> p.getSabor().name(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhum"));


        resumo.setTamanhoMaisVendido(pedidos.stream()
                .collect(Collectors.groupingBy(p -> p.getTamanho().name(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhum"));


        resumo.setDiaMaisMovimentado(pedidos.stream()
                .collect(Collectors.groupingBy(p -> p.getDataPedido().toString(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhum"));

        return resumo;
    }
}