package piraozada.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import piraozada.domain.entity.Custo;
import piraozada.repository.CustoRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustoService {

    private final CustoRepository custoRepository;

    public Custo lancarCusto(Custo custo) {

        BigDecimal total = custo.getValorUnitario()
                .multiply(new BigDecimal(custo.getQuantidade()));
        custo.setValorTotal(total);
        return custoRepository.save(custo);
    }

    public List<Custo> listarTodos() {
        return custoRepository.findAll();
    }
}