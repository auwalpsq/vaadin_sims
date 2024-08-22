/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.entities;

/**
 *
 * @author Auwal Usman
 */
public enum TypeOfBingel {
    STUDENT("Student"), GUARDIAN("Guardian"), STAFF("Staff");
    
    private String type;
    
    TypeOfBingel(String type){
        this.type = type;
    }
    
    public String getType(){return type;}
}
