package com.support.schedular;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.support.schedular.*"})
@EntityScan("com.support.schedular.entities")
public class AnnotationConfig {

}
