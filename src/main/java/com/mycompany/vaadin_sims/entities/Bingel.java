/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Auwal Usman
 */

@Entity
public class Bingel extends AbstractBingel{
    @NotEmpty
    private String firstName;
    
    private String otherNames;
    
    @NotEmpty
    private String lastName;
    
    @NotEmpty
    private String gender;
    
    @NotEmpty
    private String type;
    
    private LocalDate registrationDate;
    
    @ManyToOne
    Bingel parent;
    
    @OneToMany(mappedBy="parent", fetch=FetchType.EAGER)
    List<Bingel> bikkoi = new LinkedList<>();
    
    public Bingel(){registrationDate = LocalDate.now();}
    
    public List<Bingel> getBikkoi(){return bikkoi;}
    public void setBikkoi(List<Bingel> bikkoi){this.bikkoi = bikkoi;}
    
    public Bingel getParent(){return parent;}
    public void setParent(Bingel parent){this.parent = parent;}
    
    public LocalDate getRegistrationDate(){return registrationDate;}
    public String getFirstName(){return firstName;}
    public void setFirstName(String firstName){this.firstName = firstName;}
    
    public String getLastName(){return lastName;}
    public void setlastName(String lastName){this.lastName = lastName;}
    
    public String getOtherNames(){return otherNames;}
    public void setOtherNames(String otherNames){this.otherNames = otherNames;}
    
    public String getGender(){return gender;}
    public void setGender(String gender){this.gender = gender;}
    
    public String getType(){return type;}
    public void setType(String type){this.type = type;}
}