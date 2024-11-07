package co.acortador.controllers.custom.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> {
    private List<T> contenido;
    private int paginaActual;
    private int paginasTotales;
    private int tamano;
    public PageResponse(Page<T> page) {
        this.contenido=page.getContent();
        this.paginaActual=page.getNumber();
        this.paginasTotales=page.getTotalPages();
        this.tamano=page.getSize();
    }
}
