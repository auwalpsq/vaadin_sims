/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

/**
 *
 * @author Auwal Usman
 */
@Entity
public class Goddo extends AbstractBingel{
    private String address;
    
    @NotEmpty
    @Email
    @Column(unique=true)
    private String email;
    
    @Pattern(regexp="0\\d{10}", message="invalid phone number")
    @Column(unique=true)
    private String phone;
    
    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}
    
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    
    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone = phone;}
}
