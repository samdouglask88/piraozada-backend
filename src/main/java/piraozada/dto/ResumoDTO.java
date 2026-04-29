package piraozada.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ResumoDTO {

    private BigDecimal faturamento;
    private BigDecimal custoTotal;
    private BigDecimal lucro;
    private BigDecimal ticketMedio;
    private String saborMaisVendido;
    private String tamanhoMaisVendido;
    private String diaMaisMovimentado;
}