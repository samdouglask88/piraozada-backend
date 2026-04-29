package piraozada.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class FechamentoDTO {

    private BigDecimal valorInvestido;
    private BigDecimal faturamento;
    private BigDecimal custoTotal;
    private BigDecimal lucro;
    private BigDecimal roas;
}