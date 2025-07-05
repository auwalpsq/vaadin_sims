/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author Auwal Usman
 */
@Entity
public class Applicant extends AbstractBingel{
    
    @NotEmpty
    private String permissionToTreat;
    
    @NotEmpty
    private String specialNeed;
    private String specialNeedExplain;
    
    @NotEmpty
    private String allergy;
    private String allergyExplain;
    private String allergyPrevent;
    
    @NotEmpty
    private String medication;
    private String medicationExplain;
    
    private String mix;
    
    private String fear;
    
    private String upset;
    
    private String discipline;
    
    private String reward;
    
    private String school;
    
    private String previousClass;
    
    private String language;
    
    private String foodAllergy;
    
    private String otherInformation;
    
    private String passportFile;
    
    private LocalDate startDate;
    
    private LocalTime startTime;
    
    private LocalDateTime lastUpdated;
    
    public Applicant(){
        startDate = LocalDate.now();
        startTime = LocalTime.now();
        lastUpdated = LocalDateTime.now();
    }
    public LocalDate getStartDate(){return startDate;}
    public LocalTime getStartTime(){return startTime;}
    
    public LocalDateTime getLastUpdated(){return lastUpdated;}
    public void setLastUpdated(LocalDateTime lastUpdated){this.lastUpdated = lastUpdated;}
    
    public String getPermissionToTreat(){return permissionToTreat;}
    public void setPermissionToTreat(String permissionToTreat){this.permissionToTreat = permissionToTreat;}
    
    public String getSpecialNeed(){return specialNeed;}
    public void setSpecialNeed(String specialNeed){this.specialNeed = specialNeed;}
    public String getSpecialNeedExplain(){return specialNeedExplain;}
    public void setSpecialNeedExplain(String specialNeedExplain){this.specialNeedExplain = specialNeedExplain;}
    
    public String getAllergy(){return allergy;}
    public void setAllergy(String allergy){this.allergy = allergy;}
    public String getAllergyExplain(){return allergyExplain;}
    public void setAllergyExplain(String allergyExplain){this.allergyExplain = allergyExplain;}
    public String getAllergyPrevent(){return allergyPrevent;}
    public void setAllergyPrevent(String allergyPrevent){this.allergyPrevent = allergyPrevent;}
    
    public String getMedication(){return medication;}
    public void setMedication(String medication){this.medication = medication;}
    public String getMedicationExplain(){return medicationExplain;}
    public void setMedicationExplain(String medicationExplain){this.medicationExplain = medicationExplain;}
    
    public String getMix(){return mix;}
    public void setMix(String mix){this.mix = mix;}
    
    public String getFear(){return fear;}
    public void setFear(String fear){this.fear = fear;}
    
    public String getUpset(){return upset;}
    public void setUpset(String upset){this.upset = upset;}
    
    public String getDiscipline(){return discipline;}
    public void setDiscipline(String discipline){this.discipline = discipline;}
    
    public String getReward(){return reward;}
    public void setReward(String reward){this.reward = reward;}
    
    public String getSchool(){return school;}
    public void setSchool(String school){this.school = school;}
    
    public String getPreviousClass(){return previousClass;}
    public void setPreviousClass(String previousClass){this.previousClass = previousClass;}
    
    public String getLanguage(){return language;}
    public void setLanguage(String language){this.language = language;}
    
    public String getFoodAllergy(){return foodAllergy;}
    public void setFoodAllergy(String foodAllergy){this.foodAllergy = foodAllergy;}
    
    public String getOtherInformation(){return otherInformation;}
    public void setOtherInformation(String otherInformation){this.otherInformation = otherInformation;}
    
    public String getPassportFile(){return passportFile;}
    public void setPassportFile(String passportFile){this.passportFile = passportFile;}
}
