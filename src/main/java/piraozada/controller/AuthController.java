package piraozada.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import piraozada.domain.entity.Usuario;
import piraozada.service.JwtService;
import piraozada.service.UsuarioService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String senha = body.get("senha");

        return usuarioService.buscarPorUsername(username)
                .filter(u -> passwordEncoder.matches(senha, u.getSenha()))
                .map(u -> {
                    String token = jwtService.gerarToken(u.getUsername(), u.getRole());
                    return ResponseEntity.ok(Map.of("token", token));
                })
                .orElse(ResponseEntity.status(401).build());
    }


    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody Map<String, String> body) {
        Usuario usuario = usuarioService.criarUsuario(
                body.get("username"),
                body.get("senha"),
                body.get("role"),
                body.get("perguntaSecreta"),
                body.get("respostaSecreta")
        );
        return ResponseEntity.ok(Map.of("mensagem", "Usuário criado com sucesso!", "id", usuario.getId()));
    }


    @PostMapping("/esqueci-senha")
    public ResponseEntity<?> esqueciSenha(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String resposta = body.get("respostaSecreta");

        boolean respostaCorreta = usuarioService.verificarRespostaSecreta(username, resposta);

        if (!respostaCorreta) {
            return ResponseEntity.status(401).body(Map.of("mensagem", "Resposta incorreta!"));
        }


        return ResponseEntity.ok(Map.of("mensagem", "Resposta correta! Em breve: troca de senha."));
    }
}