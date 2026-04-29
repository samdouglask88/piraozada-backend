package piraozada.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import piraozada.dto.FechamentoDTO;
import piraozada.service.FechamentoService;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/fechamento")
@RequiredArgsConstructor
public class FechamentoController {

    private final FechamentoService fechamentoService;


    @PostMapping
    public ResponseEntity<FechamentoDTO> gerarFechamento(@RequestBody Map<String, BigDecimal> body) {
        BigDecimal valorInvestido = body.get("valorInvestido");
        return ResponseEntity.ok(fechamentoService.gerarFechamento(valorInvestido));
    }
}