package org.binar.bioskop.challenge4.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI reservasiBioskopOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("Bioskop Reservasi Open API")

                ).servers(List.of(new Server().url("https://anggareservasibioskop-production.up.railway.app/")));
    }
}
