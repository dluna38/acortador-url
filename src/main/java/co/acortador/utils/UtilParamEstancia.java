/*
package co.acortador.utils;

import co.edu.iudigital.parqueadero.exceptions.ValidationException;
import co.edu.iudigital.parqueadero.models.Dueno;
import co.edu.iudigital.parqueadero.models.Estancia;
import co.edu.iudigital.parqueadero.models.Vehiculo;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.PatternSyntaxException;

public class UtilParamEstancia {

    public Specification<Estancia> getSpecificationFilter(Map<String, String> filtros){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            final String placa = "placa";
            final String documento = "documento";
            final String fechaEntrada = "fechaEntrada";
            final String fechaSalida = "fechaSalida";
            final String estanciaCompleta = "completas";
            final String estanciaAbierta = "abiertas";

            if(filtros.containsKey("estancia")){
                if(filtros.get("estancia").equalsIgnoreCase(estanciaCompleta)){
                    predicates.add(cb.isNotNull(root.get(fechaSalida)));
                } else if (filtros.get("estancia").equalsIgnoreCase(estanciaAbierta)) {
                    predicates.add(cb.isNull(root.get(fechaSalida)));
                }
            }

            if(filtros.containsKey(placa) || filtros.containsKey(documento)){
                Join<Estancia, Vehiculo> vehiculoJoin = root.join("vehiculo", JoinType.INNER);
                Join<Vehiculo, Dueno> duenoJoin = vehiculoJoin.join("dueno",JoinType.INNER);

                if(filtros.containsKey(placa)) {
                    predicates.add(cb.equal(vehiculoJoin.get(placa), filtros.get(placa).toUpperCase()));
                }else if(filtros.containsKey(documento)){
                    predicates.add(cb.equal(duenoJoin.get("documentoIdentificacion"), filtros.get(documento).toUpperCase()));
                }
            }

            if(filtros.containsKey("fe")){
                //gt,yyyy-mm-dd,lt,yyyy-mm-dd
                predicates.add(getPredicatesFechasFilter(root,cb,fechaEntrada,getMapFechaFilter(filtros.get("fe"))));
            }
            if(filtros.containsKey("fs")){
                //gt,yyyy-mm-dd,lt,yyyy-mm-dd
                predicates.add(getPredicatesFechasFilter(root,cb,fechaSalida,getMapFechaFilter(filtros.get("fs"))));
            }
            root.fetch("vehiculo");
            root.fetch("celda");
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Predicate getPredicatesFechasFilter(Root<Estancia> root, CriteriaBuilder cb, String campo, Map<String, LocalDateTime> filtros){
        List<Predicate> predicates = new ArrayList<>();
        for(Map.Entry<String,LocalDateTime> entry : filtros.entrySet()){
            if(entry.getKey().equalsIgnoreCase("gt")){
                predicates.add(cb.greaterThanOrEqualTo(root.get(campo),entry.getValue()));
            }else if(entry.getKey().equalsIgnoreCase("lt")){
                predicates.add(cb.lessThanOrEqualTo(root.get(campo),entry.getValue()));
            }
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }
    private Map<String, LocalDateTime> getMapFechaFilter(String cadenaCompuesta){
        Map<String,LocalDateTime> filterFechasMap = new HashMap<>();
        try {
            String[] separado = cadenaCompuesta.split(",");
            if(separado.length != 4 && separado.length != 2) {
                throw new ValidationException();
            }
            for(byte i=0;i<= (separado.length/2);i+=2){
                if(separado[i+1].contains("T")) {
                    filterFechasMap.put(separado[i], LocalDateTime.parse(separado[i + 1]));
                }else {
                    filterFechasMap.put(separado[i],  LocalDateTime.of(LocalDate.parse(separado[i + 1]), LocalTime.MIDNIGHT));
                }
            }
            return filterFechasMap;
        } catch (ValidationException e) {
            throw new ValidationException("param: fecha","el parametro no tienen la longitud indicada(2 o 4), org: "+cadenaCompuesta);
        } catch (DateTimeParseException e){
            throw new ValidationException("param: fecha","No se pudo convertir la fecha: "+e.getParsedString());
        } catch (PatternSyntaxException e){
            throw new ValidationException("param: fecha","no se entiende el filtro: "+e.getIndex());
        }
    }
}
*/
