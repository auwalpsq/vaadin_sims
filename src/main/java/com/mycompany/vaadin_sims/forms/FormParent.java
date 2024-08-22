/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.forms;

import com.mycompany.vaadin_sims.entities.Parent;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

/**
 *
 * @author Auwal Usman
 */
public class FormParent extends FormLayout{
    private TextField occupation = new TextField();
    
    Binder<Parent> binder = new BeanValidationBinder<>(Parent.class);
    
    public FormParent(){
        binder.bindInstanceFields(this);
        
        this.setResponsiveSteps(new ResponsiveStep("0", 1));
        
        addFormItem(occupation, "Occupation");
        occupation.setSizeFull();
    }
    
    public Parent getTheParent(){return binder.getBean();}
    public void setTheParent(Parent parent){
        if(parent == null){
            binder.setBean(new Parent());
        }else{
            binder.setBean(parent);
        }
    }
}