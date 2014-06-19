package Utils;

/**
 *
 * @author juanma
 */
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;



public class LoggingProducer {
@Produces
public Logger produceLogger(InjectionPoint injectionPoint) throws IOException {
//    FileHandler fileHandler = new FileHandler("myLogFile");
//    Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName()).addHandler(fileHandler);
    
    try {
        String filePath="./";
      FileHandler fileHandler = new FileHandler("myapp.log", 5242880, 5, true);
      fileHandler.setFormatter(new SimpleFormatter());
      Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName()).addHandler(fileHandler);
    } catch (IOException e) {}
    
    return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
   }
}
