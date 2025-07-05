/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.entities;

import jakarta.persistence.Entity;

/**
 *
 * @author Auwal Usman
 */
@Entity
public class Parent extends AbstractBingel{
    
    private String occupation;
    
    private String placeOfWork;
    
    public String getOccupation(){return occupation;}
    public void setOccupation(String occupation){this.occupation = occupation;}
    
    public String getPlaceOfWork(){return placeOfWork;}
    public void setPlaceOfWork(String placeOfWork){this.placeOfWork = placeOfWork;}
}
