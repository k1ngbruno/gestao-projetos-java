package br.com.a3.gestaoprojetos.util;

import br.com.a3.gestaoprojetos.exception.ValidationException;

public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static void requireNotBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException("O campo " + fieldName + " é obrigatório.");
        }
    }

    public static void requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new ValidationException("O campo " + fieldName + " deve ser maior que zero.");
        }
    }

    public static void requireValidCpf(String cpf) {
        requireNotBlank(cpf, "CPF");
        String digits = cpf.replaceAll("\\D", "");
        if (digits.length() != 11) {
            throw new ValidationException("CPF inválido. Informe 11 dígitos.");
        }
    }

    public static void requireValidEmail(String email) {
        requireNotBlank(email, "e-mail");
        if (!email.contains("@") || !email.contains(".")) {
            throw new ValidationException("E-mail inválido.");
        }
    }
}
