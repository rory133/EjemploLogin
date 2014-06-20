/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

/**
 *
 * @author juanma
 */
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.Priority;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 *         This interceptor catches exception and displayes them in a JSF page
 */
@Interceptor
@CatchException
@Priority(150)
@Loggable
public class ExceptionInterceptor implements Serializable {

    @Inject
    private Logger log;

    @AroundInvoke
    public Object catchException(InvocationContext ic) throws Exception {
        try {
            return ic.proceed();
        } catch (Exception e) {
            System.out.println("*catchException");
            addErrorMessage("prubaaaaaaaaaaaaaa "+e.getMessage());
         log.severe("/!\\ " + ic.getTarget().getClass().getName() + " - " + ic.getMethod().getName() + " - " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // TODO to refactor with Controller methods
    protected void addErrorMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR , message +"   holita", null));
    }
}