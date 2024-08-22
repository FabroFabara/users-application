package com.evaluation.ec;


import com.evaluation.ec.common.GenericConverterUtils;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EntityScan
public class ServiceConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public GenericConverterUtils genericConverterUtils(ModelMapper modelMapper) {
        return new GenericConverterUtils(modelMapper);
    }
}
