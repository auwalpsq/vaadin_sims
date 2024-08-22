package com.mycompany.vaadin_sims;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@Theme(variant=Lumo.LIGHT)
public class Application implements AppShellConfigurator{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
