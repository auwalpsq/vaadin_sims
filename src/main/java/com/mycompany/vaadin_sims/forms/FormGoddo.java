/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.forms;

import com.mycompany.vaadin_sims.entities.Goddo;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

/**
 *
 * @author Auwal Usman
 */
public class FormGoddo extends FormLayout{
    private EmailField email = new EmailField();
    private TextField phone = new TextField();
    private TextField address = new TextField();
    
    Binder<Goddo> binder = new BeanValidationBinder<>(Goddo.class);
    
    public FormGoddo(){
        binder.setBean(new Goddo());
        
        setResponsiveSteps(new ResponsiveStep("0", 1));
        
        addFormItem(email, "Email");
        addFormItem(phone, "Phone");
        addFormItem(address, "Address");
        email.setSizeFull();
        phone.setSizeFull();
        address.setSizeFull();
        
        binder.bindInstanceFields(this);
    }
    
    public Goddo getGoddo(){
        return binder.getBean();
    }
    public void setGoddo(Goddo goddo){
        if(goddo == null){
            binder.setBean(new Goddo());
        }else{
            binder.setBean(goddo);
        }
        
    }
}
