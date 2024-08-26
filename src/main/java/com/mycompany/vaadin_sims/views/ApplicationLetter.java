/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 *
 * @author Auwal Usman
 */
@Route("application_letter")
public class ApplicationLetter extends VerticalLayout{
    private SchoolInfoView schoolInfo = new SchoolInfoView();
    
    public ApplicationLetter(){
        add(schoolInfo);
    }
}
