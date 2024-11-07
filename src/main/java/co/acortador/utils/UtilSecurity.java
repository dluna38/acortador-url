package co.acortador.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UtilSecurity {

    public static UserDetails getActualUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return  auth != null ? (UserDetails) auth.getPrincipal() : null;
    }
}
