package br.com.cds.ebc.collaborator.service;

import br.com.cds.ebc.collaborator.dao.UserDAO;
import br.com.cds.ebc.collaborator.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by Eduardo on 08/01/2017.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException{

       User user = userDAO.findUserByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("Conta para o usuario \""
                    + username + "\" nao encontrada.");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
