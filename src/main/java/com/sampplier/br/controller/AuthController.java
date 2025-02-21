package com.sampplier.br.controller;


import com.sampplier.br.model.Usuario;
import com.sampplier.br.service.AuthService;
import com.sampplier.br.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(authService.registrarUsuario(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        var user = authService.buscarPorUsername(usuario.getUsername());
        if (user.isPresent() && user.get().getSenha().equals(usuario.getSenha())) {
            return ResponseEntity.ok(jwtUtil.gerarToken(usuario.getUsername()));
        }
        return ResponseEntity.status(401).build();
    }
}
