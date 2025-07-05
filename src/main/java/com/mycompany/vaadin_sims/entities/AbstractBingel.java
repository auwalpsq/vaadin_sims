/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 *
 * @author Auwal Usman
 */
@MappedSuperclass
public class AbstractBingel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    
    @Override
    public int hashCode(){
        if(getId() != null){
            return getId().hashCode();
        }
        
        return super.hashCode();
    }  
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Bingel that)){
            return false;
        }
        
        if(getId() != null){
            return this.getId().equals(that.getId());
        }
        
        return super.equals(obj);
    }
}
