package com.trejo.api_buses_backend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Suponiendo que esta es la contraseña que tienes en texto plano
        String plainPassword = "password123";

        // Encriptando la contraseña
        String encryptedPassword = encoder.encode(plainPassword);

        System.out.println("Contraseña encriptada: " + encryptedPassword);
    }
}
