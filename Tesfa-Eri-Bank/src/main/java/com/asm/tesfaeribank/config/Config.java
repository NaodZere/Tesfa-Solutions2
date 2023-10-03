package com.asm.tesfaeribank.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.asm.tesfaeribank.repository")
public class Config extends AbstractMongoClientConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();

    }

    @Override
    protected String getDatabaseName() {

        return "AugustBank";
    }
}
