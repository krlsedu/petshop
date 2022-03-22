package br.com.cursojava.petshop.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .build().useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, getRespostasPadrao())
                .globalResponseMessage(RequestMethod.GET, getRespostasPadrao())
                .globalResponseMessage(RequestMethod.PUT, getRespostasPadrao())
                .globalResponseMessage(RequestMethod.DELETE, getRespostasPadrao())
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API Petshop",
                "API Petshop",
                "API 1.0",
                "",
                new Contact("Petshop", "www.petshop.com.br", "petshop@pet.com.br"),
                "",
                "",
                Collections.emptyList()
        );
    }

    private List<ResponseMessage> getRespostasPadrao() {
        List<ResponseMessage> config = new ArrayList<>();

        config.add(new ResponseMessageBuilder()
                .code(400)
                .message("Erro nos dados informados!")
//                .responseModel(new ModelRef("Error"))
                .build());

        config.add(new ResponseMessageBuilder()
                .code(401)
                .message("Não autorizado! Necessário token de autenticação!")
//                .responseModel(new ModelRef("Error"))
                .build());

        config.add(new ResponseMessageBuilder()
                .code(403)
                .message("Não permitido! O usuário autenticado não tem permissão de acesso!")
//                .responseModel(new ModelRef("Error"))
                .build());

        config.add(new ResponseMessageBuilder()
                .code(500)
                .message("Erro não esperado no servidor!")
//                .responseModel(new ModelRef("Error"))
                .build());

        return config;
    }
}
