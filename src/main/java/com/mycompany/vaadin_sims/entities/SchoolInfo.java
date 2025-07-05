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
    NAME("AR-RIYADH GENERIC SCHOOLS"), 
    APP("STUDENT INFORMATION MANAGEMENT SYSTEM"),
    ADDRESS_1("SARDAUNA STREET, ADJACENT TO L.E.A PRIMARY SCHOOL"),
    ADDRESS_2("ANGUWAR FULANI, DAKWA, NIGER STATE"),
    EMAIL("email"),
    PHONE("phone"),
    LOGO_PATH("images/logo.jpeg");
    
    private String data;
    
    SchoolInfo(String data){
        this.data = data;
    }
    
    public String getData(){return data;}
    
}
