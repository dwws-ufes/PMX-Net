package br.ifes.pmxnet.controle.usuario;

import br.ifes.pmxnet.dominio.Usuario;
import br.ifes.pmxnet.persistencia.usuario.IUsuarioRepository;
import br.ifes.pmxnet.seguranca.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthControle {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Optional<Usuario> encontrado = usuarioRepository.findByEmail(usuario.getEmail());
        System.out.print("************");
        System.out.print(encontrado);
        if (!encontrado.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuário ou senha inválidos");
        }

        if (!passwordEncoder().matches(usuario.getSenha(), encontrado.get().getSenha())) {
            ResponseEntity.status(401).body("Credenciais inválidas");
        }
        if (encontrado.isPresent()) {
            // Gerando token com id, email e role
            String token = JwtUtil.generateToken(encontrado.get().getEmail(),
                    encontrado.get().getId());

            Map<String, String> resposta = new HashMap<>();
            resposta.put("token", token);
            return ResponseEntity.ok(resposta);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        usuario.setSenha(passwordEncoder().encode(usuario.getSenha()));
        Usuario salvo = usuarioRepository.save(usuario);

        // (opcional) retorna o token já após o registro
        String token = JwtUtil.generateToken(salvo.getEmail(), salvo.getId());
        Map<String, String> resposta = new HashMap<>();
        resposta.put("token", token);
        return ResponseEntity.ok(resposta);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
