import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author niti.gupta
 */
@Slf4j
@SpringBootApplication
@ComponentScan("com.tfsc.ilabs.*")
public class VideoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try{
            log.info("APPLICATION STARTED");
        }
        catch(Exception e){
            log.error("Unable to process incoming events",e);
        }
    }
}
