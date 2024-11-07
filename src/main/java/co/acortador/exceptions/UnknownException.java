package co.acortador.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class UnknownException extends AppHttpException{
    public UnknownException(String... causas) {
        super("Error desconocido", HttpStatus.INTERNAL_SERVER_ERROR);
        addToExtraContent(Map.of("Causas",String.join("\n",causas)));
    }
}
