package co.acortador.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class UtilAcortador {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /*public String generateShortCode(String originalUrl) {
        // Generar un número aleatorio
        long id = Random.random(10, true, true).hashCode();

        // Convertir el número a base 62
        String shortCode = "";
        while (id > 0) {
            shortCode = ALPHABET.charAt((int) (id % 62)) + shortCode;
            id /= 62;
        }

        // Verificar si el código ya existe
        *//*while (urlRepository.existsById(shortCode)) {
            id = RandomStringUtils.random(10, true, true).hashCode();
            shortCode = "";
            while (id > 0) {
                shortCode = ALPHABET.charAt((int) (id % 62)) + shortCode;
                id /= 62;
            }
        }*//*
        return shortCode;
    }*/

    public static String generateShortCode(int length) throws NoSuchAlgorithmException {
        StringBuilder code = new  StringBuilder();
        SecureRandom random = SecureRandom.getInstanceStrong();
        for (int i = 0; i < length; i++) {
            code.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return code.toString();
    }
}
