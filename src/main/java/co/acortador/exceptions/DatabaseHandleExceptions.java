package co.acortador.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.Map;

public class DatabaseHandleExceptions extends AppHttpException{
    private final SQLException sqlException;
    private HttpStatus mySQLHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    public DatabaseHandleExceptions(SQLException exception) {
        super("Error en la Base de Datos", HttpStatus.INTERNAL_SERVER_ERROR);
        this.sqlException=exception;
        addToExtraContent(Map.of("Causa",sqlException.getMessage()));
        //addCause();
    }

    /*private void addCause(){
        String message = null;
        String specificCause = null;
        switch (sqlException.getSQLState()){
            case "23505":
                message = "Se produjo una violación de la restricción impuesta por un índice único o una restricción única";
                break;
            default: message = "Error no manejado";
        }

    }*/
}
