package com.app.loginAndRegister.security;
import com.app.loginAndRegister.dto.UserDto;
import com.app.loginAndRegister.exception.SpringException;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import com.app.loginAndRegister.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Component
public class UseDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserService userService;


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {

            UserDto userDto = userService.findByUsername(username);


        if (userDto != null) {

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + userDto.getRol().toString());
            permisos.add(p1);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", userDto);

            User user = new User(userDto.getUsername(), userDto.getPassword(), permisos);

            return user;

        } else {

            return null;
        }
    }

}
