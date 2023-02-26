package com.example.demo.Config;

import com.example.demo.Entities.Grootouders;
import com.example.demo.Entities.Ouders;
import com.example.demo.Repositories.GrootoudersRep;
import com.example.demo.Repositories.OudersRep;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@CrossOrigin
@Configuration
public class Config implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }


    @Bean
    CommandLineRunner commandLineRunner(GrootoudersRep grootoudersRep, OudersRep oudersRep) {
        return args -> {
            Grootouders oma = new Grootouders(1, "oma");
            Grootouders opa = new Grootouders(2, "opa");
            grootoudersRep.saveAll(List.of(oma, opa));

            Ouders toos = new Ouders(null, 2, 1, "Toos", "", false);
            Ouders timo = new Ouders(null, 2, 1, "Timo", "", false);
            Ouders rikie = new Ouders(null, 2, 1, "Rikie", "", false);
            Ouders annie = new Ouders(null, 2, 1, "Annie", "", false);
            Ouders antoon = new Ouders(null, 2, 1, "Antoon", "", false);
            Ouders evert = new Ouders(null, 2, 1, "Evert", "", false);
            Ouders fieke = new Ouders(null, 2, 1, "Fieke", "", false);
            Ouders els = new Ouders(null, 2, 1, "Els", "", false);
            Ouders dirk = new Ouders(null, 2, 1, "Dirk", "", false);
            Ouders emelien = new Ouders(null, 2, 1, "Emelien", "", false);
            Ouders gerard = new Ouders(null, 2, 1, "Gerard", "", false);
            Ouders hein = new Ouders(null, 2, 1, "Hein", "", false);
            Ouders roelof = new Ouders(null, 2, 1, "Roelof", "", false);

            oudersRep.saveAll(List.of(toos, timo,rikie, annie, antoon, evert, fieke, els, dirk, emelien, gerard, hein, roelof));

        };
    }



}
