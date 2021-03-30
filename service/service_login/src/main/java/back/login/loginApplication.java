package back.login;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"back"})
public class loginApplication {
    public static void main(String[] args) {
        SpringApplication.run(loginApplication.class,args);
    }
}
