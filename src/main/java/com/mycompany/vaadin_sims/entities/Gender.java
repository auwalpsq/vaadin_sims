/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.entities;

/**
 *
 * @author Auwal Usman
 */
public enum Gender {
    MALE("Male"), FEMALE("Female");
    
    private String gender;
    
    private Gender(String gender){
        this.gender = gender;
    }
    
    public String getGender(){return gender;}
}
