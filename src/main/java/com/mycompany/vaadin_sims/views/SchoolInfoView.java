/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.views;

import com.mycompany.vaadin_sims.entities.SchoolInfo;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 *
 * @author Auwal Usman
 */
public class SchoolInfoView extends VerticalLayout{
    public SchoolInfoView(){
        this.setPadding(false);
        this.setMargin(false);
        this.setSpacing(false);
        
        H3 name = new H3(SchoolInfo.NAME.getData());
        H4 address1 = new H4(SchoolInfo.ADDRESS_1.getData());
        H4 address2 = new H4(SchoolInfo.ADDRESS_2.getData());
        Image logo = new Image(SchoolInfo.LOGO_PATH.getData(), "logo to appear here");
        logo.setHeight("100px");
        Span email = new Span(SchoolInfo.EMAIL.getData());
        Span phone = new Span(SchoolInfo.PHONE.getData());
        

        this.setSpacing(false);
        this.setAlignItems(Alignment.CENTER);

        logo.addClassNames(LumoUtility.Border.ALL);
        logo.getStyle().set("border-radius", "60%");
        
        add(logo, name, address1, address2, email, phone);
    }
}
