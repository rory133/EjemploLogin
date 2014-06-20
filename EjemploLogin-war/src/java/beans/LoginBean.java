/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import Utils.Loggable;
import entidades.Login;
import entidades.Usuario;
import facade.LoginFacade;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
/**
 *
 * @author juanma
 */
@Loggable
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {
//    @Inject
//    @Default
//    private Logger logger;
            
    @EJB
    private LoginFacade loginFacade;
    private Login loginActual;

    public Login getLoginActual() {
        System.out.println("*getLoginAcutal");
        //System.out.println(getClass().getClassLoader().getResource("bean.xml"));
        //System.out.println(ClassName.class.getClassLoader().getResource("logging.properties"));
        if (loginActual ==null){
            System.out.println("***getLoginAcutal loginAcutal==null");
            loginActual=(new Login());
            loginActual.setUsuarioIdusuario(new Usuario());
        }
        
        return loginActual;
    }

    public void setLoginActual(Login loginActual) {
        this.loginActual = loginActual;
    }
    
    
    
    public LoginBean() { 
    }
    
    public String validarLogin(){
        String outcome="index";
        try {
            Login l=loginFacade.validarLogin(getLoginActual().getLogin(), getLoginActual().getPassword());
            if (l !=null){
                outcome="/app/principal?Faces-redirect=true";
            }else{
                FacesContext.getCurrentInstance().addMessage("error al hacer login", new FacesMessage("login/password invalidos"));
            }
        }
        catch (Exception e){
//            logger.info("error al validar usuario "+ e );
            
        }
        return outcome;
    }
}
