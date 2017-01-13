package br.com.cds.ebc.collaborator.service;

import br.com.cds.ebc.collaborator.dao.GenericDAO;
import br.com.cds.ebc.collaborator.dao.UserDAO;
import br.com.cds.ebc.collaborator.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Eduardo on 08/01/2017.
 */
@Service
public class UserService extends ServiceBase<User, Integer>{

    @Resource(name = "authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDAO dao;

    @Override
    protected GenericDAO<User, Integer> getDAO() {
        return dao;
    }

    public String getUserLogin() {
        User user = dao.findUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return user != null ? user.getName() : "";
    }

    public boolean login(String username, String password){
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (authenticate.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                return true;
            }
        }catch (AuthenticationException e){

        }
        return false;
    }

    public void logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public User findUserByLogin(String username){
        return dao.findUserByLogin(username);
    }

}
