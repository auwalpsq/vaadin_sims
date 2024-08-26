/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.views;

import com.mycompany.vaadin_sims.entities.SchoolInfo;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 *
 * @author Auwal Usman
 */
public class SchoolInfoView extends VerticalLayout{
    public SchoolInfoView(){
        this.setPadding(false);
        this.setMargin(false);
        
        HorizontalLayout title = new HorizontalLayout();
        H3 name = new H3(SchoolInfo.NAME.getData());
        
        title.add(name);
        add(title);
        
        H4 address = new H4(SchoolInfo.ADDRESS.getData());
        add(address);
    }
    
}
