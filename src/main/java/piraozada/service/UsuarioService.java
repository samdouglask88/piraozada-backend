package piraozada.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import piraozada.domain.entity.Usuario;
import piraozada.repository.UsuarioRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public Usuario criarUsuario(String username, String senha, String role,
                                String perguntaSecreta, String respostaSecreta) {

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setRole(role);
        usuario.setPerguntaSecreta(perguntaSecreta);
        usuario.setRespostaSecreta(passwordEncoder.encode(respostaSecreta));
        return usuarioRepository.save(usuario);
    }


    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }


    public boolean verificarRespostaSecreta(String username, String resposta) {
        return usuarioRepository.findByUsername(username)
                .map(u -> passwordEncoder.matches(resposta, u.getRespostaSecreta()))
                .orElse(false);
    }
}