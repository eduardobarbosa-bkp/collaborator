package br.com.cds.ebc.collaborator.controller;

import br.com.cds.ebc.collaborator.entity.User;
import br.com.cds.ebc.collaborator.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Eduardo on 06/01/2017.
 */
@Controller
@Scope("request")
public class LoginController {

    @Autowired
    private UserService userService;

    private String username;

    private String password;

    private String loggedUser;

    public String getLoggedUser() {
        return userService.getUserLogin();
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login() throws IOException {
        this.login(this.username, this.password, true);
    }

    public void logout() throws IOException{
       userService.logout();
       FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
    }

    public void loginAuth2() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String username = (String) map.get("username");
        String token = (String) map.get("token");
        String name = (String) map.get("name");
        User user = userService.findUserByLogin(username);
        if(user == null) {
            user = new User();
        }
        user.setUsername(username);
        user.setPassword(token);
        user.setName(name);
        userService.update(user);
        this.login(username, token, false);
    }

    public void login(String username, String password, boolean encrypt) throws IOException {
        boolean login = userService.login(username, encrypt ? DigestUtils.md5Hex(password) : password);
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;
        boolean loggedIn;
        if(login) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Bem vindo!");
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login", "usuario ou senha invalidos!");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        if(loggedIn){
            FacesContext.getCurrentInstance().getExternalContext().redirect("collaborator.xhtml");
        }
    }


}
