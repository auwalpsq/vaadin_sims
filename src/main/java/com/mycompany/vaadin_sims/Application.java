package com.mycompany.vaadin_sims;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The entry point of the Spring Boot application.
 */

@PWA(
        name="Vaadin SIMS",
        shortName="SIMS",
        offlinePath="offline.html",
        offlineResources={"./images/offline.png"}
)
@SpringBootApplication
@Theme(value="sims_style")
public class Application extends SpringBootServletInitializer implements AppShellConfigurator{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}