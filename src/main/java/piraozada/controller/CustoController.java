package piraozada.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import piraozada.domain.entity.Custo;
import piraozada.service.CustoService;

import java.util.List;

@RestController
@RequestMapping("/custos")
@RequiredArgsConstructor
public class CustoController {

    private final CustoService custoService;


    @PostMapping
    public ResponseEntity<Custo> lancarCusto(@RequestBody Custo custo) {
        return ResponseEntity.ok(custoService.lancarCusto(custo));
    }


    @GetMapping
    public ResponseEntity<List<Custo>> listarTodos() {
        return ResponseEntity.ok(custoService.listarTodos());
    }
}