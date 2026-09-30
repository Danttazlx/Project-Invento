package Project_Invento.demo.infrastructore.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient pythonWebClient (){
        return  WebClient.builder().build();
    };


}
