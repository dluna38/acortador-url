package co.acortador.utils;

import co.acortador.exceptions.FieldRequiredException;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UtilString {
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    private UtilString() {
    }

    public static boolean isEmailValid(String email) {
        if (stringIsEmptyOrNull(email)) return false;
        email = email.trim();
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isStringEmpty(String cadena) {
        cadena = cadena.trim();
        return cadena.isEmpty() || cadena.isBlank();
    }

    public static boolean stringIsEmptyOrNull(String cadena) {
        return cadena == null || isStringEmpty(cadena);
    }

    public static String rodearDePorcentaje(String cadena) {
        return "%" + cadena + "%";
    }

    public static void validateRequiredField(String field, String s) {
        if (stringIsEmptyOrNull(s)) {
            throw new FieldRequiredException(field);
        }
    }
    public static String formatDate(LocalDateTime date){
        try {
            return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"));
        } catch (Exception e) {
            return "";
        }
    }
    public static String formatCurrencyNumberToCO(Number numero){
        try {
            return NumberFormat.getCurrencyInstance(new Locale("es", "CO")).format(numero);
        } catch (Exception e) {
            return "";
        }
    }

}
