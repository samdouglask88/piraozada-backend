package piraozada.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import piraozada.dto.ResumoDTO;
import piraozada.service.ResumoService;

@RestController
@RequestMapping("/resumo")
@RequiredArgsConstructor
public class ResumoController {

    private final ResumoService resumoService;

    // GET /resumo — retorna o resumo da operação
    @GetMapping
    public ResponseEntity<ResumoDTO> gerarResumo() {
        return ResponseEntity.ok(resumoService.gerarResumo());
    }
}