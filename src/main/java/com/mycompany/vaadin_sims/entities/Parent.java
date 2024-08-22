/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

/**
 *
 * @author Auwal Usman
 */
@Entity
public class Parent extends AbstractBingel{
    
    private String occupation;
    
    @OneToOne
    private Bingel bingel;
    
    public String getOccupation(){return occupation;}
    public void setOccupation(String occupation){this.occupation = occupation;}
    
    public Bingel getBingel(){return bingel;}
    public void setBingel(Bingel bingel){this.bingel = bingel;}
}
