/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import Utils.CatchException;
import Utils.Loggable;
import Utils.LoggingInterceptor;
import entidades.Login;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author juanma
 */
@Stateless
// @Interceptors(LoggingInterceptor.class)
@Loggable
@CatchException
public class LoginFacade extends AbstractFacade<Login> {
//        @Inject
//    private Logger logger;
        
    @PersistenceContext(unitName = "EjemploLogin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoginFacade() {
        super(Login.class);
    }
    
    public Login validarLogin(String loginUsuario, String password){
        Login login =null;
        try{
            Query query=
                    em.createQuery("SELECT l FROM Login l WHERE l.login = :login and l.password = :password");
            query.setParameter("login", loginUsuario);
            query.setParameter("password",password);
            login=(Login) query.getSingleResult();
            if (login!=null){
//                logger.info("validado usuario : "+loginUsuario);
            }
        }
            catch (Exception e){
//                 logger.info("error al validar usuario : "+loginUsuario+" error: "+e);
            }
                    
            
            
        return login;
    }
}
