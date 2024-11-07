package co.acortador.services;

import co.acortador.controllers.custom.response.UrlCode;
import co.acortador.exceptions.ResourceNotFoundException;
import co.acortador.exceptions.UnknownException;
import co.acortador.exceptions.ValidationException;
import co.acortador.models.Link;
import co.acortador.models.Usuario;
import co.acortador.repositories.LinkRepository;
import co.acortador.repositories.UsuarioRepository;
import co.acortador.utils.UtilAcortador;
import co.acortador.utils.UtilSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AcortadorService {
    private final LinkRepository linkRepository;
    private final UsuarioRepository usuarioRepository;

    public AcortadorService(LinkRepository linkRepository, UsuarioRepository usuarioRepository) {
        this.linkRepository = linkRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public UrlCode acortarUrl(String url){
        final int topLevelLength = 5;
        final int trysForLevel = 3;
        int currentLength = 3;
        int currentTry = 1;

        try {
            String code = UtilAcortador.generateShortCode(currentLength);
            while (linkRepository.existsByCodeAndStateIsTrue(code)){
                if(currentTry == trysForLevel){
                    if(currentLength == topLevelLength){
                        throw  new ValidationException("maxTrys","reached");
                    }
                    currentTry = 1;
                    currentLength++;
                }
                currentTry++;
                code = UtilAcortador.generateShortCode(currentLength);
            }
            UserDetails user =UtilSecurity.getActualUser();
            if(user == null){
                throw new ResourceNotFoundException("usuario no encontrado");
            }

            Usuario usr  = usuarioRepository.findUsuarioByCorreoIgnoreCase(user.getUsername()).orElseThrow(()->new ResourceNotFoundException("user not found"));
            Link newLink = Link.builder().originalUrl(url).code(code).validDays(usr.getSubscriptionType().getDays()).usuario(usr).build();
            linkRepository.save(newLink);
            return new UrlCode(code,newLink.getValidUntil().toString());
        } catch (NoSuchAlgorithmException e) {
            throw new UnknownException("No se pudo acortar el url - bad short code");
        }

    }
    public String getOriginalUrl(String code){
        return linkRepository.findByCode(code).orElseThrow(ResourceNotFoundException::new).getOriginalUrl();
    }
}
