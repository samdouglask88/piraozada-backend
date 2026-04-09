package piraozada.domain.entity;

import piraozada.enums.Sabor;
import piraozada.enums.Tamanho;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Table(name = "pedidos")
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;

    private LocalDate dataPedido;

    @Enumerated(EnumType.STRING)
    private Sabor sabor;

    @Enumerated(EnumType.STRING)
    private Tamanho tamanho;

    private Integer quantidade;

    private BigDecimal valorTotal;
}