package com.trejo.api_buses_backend.servicio.Impl;

import com.trejo.api_buses_backend.models.Usuario;
import com.trejo.api_buses_backend.repository.IUsuarioRepository;
import com.trejo.api_buses_backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final IUsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(IUsuarioRepository userRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(String username, String password) throws Exception {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            if (usuario.getPassword().equals(password)) {
                return jwtUtil.generateToken(usuario);
            } else {
                throw new Exception("Credenciales inválidas");
            }
        } else {
            throw new Exception("Usuario no encontrado");
        }
    }
}
