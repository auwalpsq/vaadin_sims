/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    
    private LocalDate birthDate;
    
    private String religion;
    
    private String nationality;
    
    @ManyToOne
    Bingel guardian;
    
    @OneToMany(mappedBy="guardian", fetch=FetchType.EAGER, orphanRemoval=true, cascade=CascadeType.ALL)
    List<Bingel> bikkoi = new LinkedList<>();
    
    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
    private Parent parent;
    
    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
    private Goddo goddo;
    
    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
    private Applicant applicant;
    
    public Bingel(){
        registrationDate = LocalDate.now();
    }
    
    public Parent getParent(){return parent;}
    public void setParent(Parent parent){this.parent = parent;}
    
    public Goddo getGoddo(){return goddo;}
    public void setGoddo(Goddo goddo){this.goddo = goddo;}
    
    public Applicant getApplicant(){return applicant;}
    public void setApplicant(Applicant applicant){this.applicant = applicant;}
    
    public List<Bingel> getBikkoi(){return bikkoi;}
    public void setBikkoi(List<Bingel> bikkoi){this.bikkoi = bikkoi;}
    
    public Bingel getGuardian(){return guardian;}
    public void setGuardian(Bingel guardian){this.guardian = guardian;}
    
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
    
    public LocalDate getBirthDate(){return birthDate;}
    public void setBirthDate(LocalDate birthDate){this.birthDate = birthDate;}
    
    public String getReligion(){return religion;}
    public void setReligion(String religion){this.religion = religion;}
    
    public String getNationality(){return nationality;}
    public void setNationality(String nationality){this.nationality = nationality;}
}