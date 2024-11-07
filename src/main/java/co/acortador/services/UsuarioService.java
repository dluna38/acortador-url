package co.acortador.services;

import co.acortador.exceptions.ValidationException;
import co.acortador.models.Usuario;
import co.acortador.repositories.UsuarioRepository;
import co.acortador.utils.UtilString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario saveUsuario(Usuario usuario){
        validateUsuario(usuario);
        usuario.setContrasena(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }


    private void validateUsuario(Usuario usuario) {
        UtilString.validateRequiredField("nombre",usuario.getNombre());
        UtilString.validateRequiredField("password",usuario.getPassword());

        if(!UtilString.isEmailValid(usuario.getCorreo())){
            throw new ValidationException("correo","no es un correo valido");
        }
        if(usuarioRepository.findUsuarioByCorreoIgnoreCase(usuario.getCorreo()).isPresent()){
            throw new ValidationException("correo","ya existe");
        }

    }

}
