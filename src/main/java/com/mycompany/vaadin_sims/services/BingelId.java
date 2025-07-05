/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.services;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

/**
 *
 * @author Auwal Usman
 */
@SpringComponent
@VaadinSessionScope
public class BingelId {
    private Long id;
    
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
}
