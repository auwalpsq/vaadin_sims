/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.views;

import com.mycompany.vaadin_sims.entities.Bingel;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author Auwal Usman
 */
public class BasicData extends FormLayout{
    public BasicData(Bingel bingel){
        this.setResponsiveSteps(new ResponsiveStep("0", 1));
        
        TextField fullName = new TextField();
        fullName.setSizeFull();
        TextField gender = new TextField();
        gender.setSizeFull();
        TextField registrationDate = new TextField();
        registrationDate.setSizeFull();
        
        fullName.setEnabled(false);
        gender.setEnabled(false);
        registrationDate.setEnabled(false);
        
        fullName.setValue(bingel.getFirstName() + " " + bingel.getLastName() + " " + bingel.getOtherNames());
        gender.setValue(bingel.getGender());
        registrationDate.setValue(bingel.getRegistrationDate().toString());
        
        addFormItem(fullName, "Full Name");
        addFormItem(gender, "Gender");
        addFormItem(registrationDate, "Registration Date");
    }
}
