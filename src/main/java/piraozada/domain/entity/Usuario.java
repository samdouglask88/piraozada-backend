package piraozada.domain.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String perguntaSecreta;  // sempre será "Qual o nome do seu dog?"

    @Column(nullable = false)
    private String respostaSecreta;  // resposta: "Skye"
}