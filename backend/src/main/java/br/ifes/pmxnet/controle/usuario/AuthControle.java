package br.ifes.pmxnet.controle.usuario;

import br.ifes.pmxnet.dominio.Usuario;
import br.ifes.pmxnet.persistencia.usuario.IUsuarioRepository;
import br.ifes.pmxnet.seguranca.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthControle {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Usuario encontrado = usuarioRepository.findAll().stream()
            .filter(u -> u.getEmail().equals(usuario.getEmail()) && u.getSenha().equals(usuario.getSenha()))
            .findFirst()
            .orElse(null);

        if (encontrado != null) {
            // Gerando token com id, email e role
            String token = JwtUtil.generateToken(
                encontrado.getEmail(),
                encontrado.getId()
            );

            Map<String, String> resposta = new HashMap<>();
            resposta.put("token", token);
            return ResponseEntity.ok(resposta);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
