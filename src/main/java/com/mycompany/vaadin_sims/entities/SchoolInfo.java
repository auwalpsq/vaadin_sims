/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.vaadin_sims.entities;

/**
 *
 * @author Auwal Usman
 */
public enum SchoolInfo {
    NAME("El-kanemi College of Islamic Theology, Yola"), 
    ADDRESS("Byepass, Yola, Adamawa State"),
    EMAIL("elkanemi@gmail.com"),
    PHONE("080123456789");
    
    private String data;
    
    SchoolInfo(String data){
        this.data = data;
    }
    
    public String getData(){return data;}
    
}
