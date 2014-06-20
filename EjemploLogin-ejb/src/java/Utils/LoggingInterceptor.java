package Utils;

/**
 *
 * @author juanma
 */
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Loggable
@Priority(100)
public class LoggingInterceptor implements Serializable {
    @Inject
    private transient  Logger logger;


    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        logger.entering(ic.getTarget().getClass().getName(), ic.getMethod().getName());
        try {
            return ic.proceed();
        } finally {
            logger.exiting(ic.getTarget().getClass().getName(), ic.getMethod().getName());
            logger.info("<<< " + ic.getTarget().getClass().getName() + "-" + ic.getMethod().getName());
        }
    }
    
    @AroundConstruct
    private void init(InvocationContext ic) throws Exception {
        logger.info("entrando en constructor ");
        try {
            ic.proceed();
        } finally {
//            logger.fine("fine- My logging interceptor constructor: Exiting");
//            logger.info("info -My logging interceptor constructor: Exiting");
            logger.info(" iniciado: "+ic.getTarget().getClass().getName());
        }
    }
    
    
}

