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
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
        H4 address = new H4(SchoolInfo.ADDRESS.getData());
        
        VerticalLayout header = new VerticalLayout();
        header.setSpacing(false);
        header.setAlignItems(Alignment.CENTER);
        header.add(name, address);
        
        
        Image logo = new Image("images/passport.jpg", "logo to appear here");
        logo.setHeight("100px");
        
        logo.addClassNames(LumoUtility.Border.ALL);
        logo.getStyle().set("border-radius", "60%");
        
        HorizontalLayout title = new HorizontalLayout();
        title.setWidthFull();
        
        title.add(logo, header);
        add(title);
        
        Span email = new Span(SchoolInfo.EMAIL.getData());
        Span phone = new Span(SchoolInfo.PHONE.getData());
        
        add(email, phone);
        this.setAlignSelf(FlexComponent.Alignment.END, email);
        this.setAlignSelf(FlexComponent.Alignment.END, phone);
    }
}
