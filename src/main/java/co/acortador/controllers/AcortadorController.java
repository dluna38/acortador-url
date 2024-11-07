package co.acortador.controllers;

import co.acortador.controllers.custom.requests.UrlRequest;
import co.acortador.controllers.custom.response.UrlCode;
import co.acortador.services.AcortadorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AcortadorController {

    private final AcortadorService acortadorService;

    public AcortadorController(AcortadorService acortadorService) {
        this.acortadorService = acortadorService;
    }

    @PostMapping("acortar")
    public ResponseEntity<UrlCode> acortarUrl(@RequestBody @NonNull UrlRequest urlRequest){
        return ResponseEntity.ok(acortadorService.acortarUrl(urlRequest.url()));
    }

    @GetMapping("url/{code}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String code){
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(acortadorService.getOriginalUrl(code));
    }
}
