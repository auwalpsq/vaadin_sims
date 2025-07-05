package com.mycompany.vaadin_sims.services;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.ServiceInitEvent;
import org.slf4j.LoggerFactory;

public class ServiceListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {

        event.getSource().addSessionInitListener(
                initEvent -> LoggerFactory.getLogger(getClass())
                        .info("A new Session has been initialized!"));

        event.getSource().addUIInitListener(
                initEvent -> LoggerFactory.getLogger(getClass())
                        .info("A new UI has been initialized!"));
    }
}
