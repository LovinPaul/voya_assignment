package ai.voya_assignment.config;  
  
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
  
@Configuration 
@ComponentScan("ai.voya_assignment") 
@EnableWebMvc   
public class AppConfig {}
