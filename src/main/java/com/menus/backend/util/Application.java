package com.menus.backend.util;

import graphql.scalars.ExtendedScalars;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Application {

    @Bean
    public RuntimeWiring.Builder configureWiring(){
        return RuntimeWiring.newRuntimeWiring()
                .scalar(ExtendedScalars.Json);
    }

}
