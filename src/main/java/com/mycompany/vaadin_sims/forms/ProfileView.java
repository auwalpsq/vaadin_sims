/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.forms;

import com.mycompany.vaadin_sims.entities.Applicant;
import com.mycompany.vaadin_sims.entities.Bingel;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Auwal Usman
 */

public class ProfileView extends ApplicationForm{
    public ProfileView(){
        binder.bindInstanceFields(this);
        binder.setReadOnly(true);
    }
    public void viewApplicant(){ 
  
        this.setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("500px", 2),
                new ResponsiveStep("1000px", 3),
                new ResponsiveStep("1500px", 4)
        );
        HorizontalLayout avatarLayout = new HorizontalLayout();
        Avatar avatar = new Avatar(bingel.getFirstName() + " " + bingel.getLastName());
        avatarLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        avatar.setWidth("100px");
        avatar.setHeight("100px");
        if(applicant != null && passportFile != null){
            avatar.setImageResource(new StreamResource(passportFile, () -> {
                try{
                    FileInputStream fis = new FileInputStream(new File("src\\main\\resources\\META-INF\\resources\\images\\" + passportFile));
                    return fis;
                }catch(IOException ex){
                    ex.printStackTrace();
                }
                return null;
            }));
            
        }
        avatarLayout.add(avatar);
        gender.setItems(bingel.getGender());
        nationality.setItems(bingel.getNationality());
        religion.setItems(bingel.getReligion());
        
        this.setColspan(avatarLayout, 4);
        
        add(avatarLayout, 
                firstName, otherNames, lastName, birthDate,
                gender, religion, nationality
        );
        if(birthDate != null && !birthDate.isEmpty()){    
            Period period = Period.between(birthDate.getValue(), LocalDate.now());
            TextField tfAge = new TextField("Age");
            tfAge.setReadOnly(true);
            tfAge.setValue(period.getYears() + " years and " + period.getMonths() % 12 + " months");
            add(tfAge);
            setColspan(tfAge, 2);
        }else{
            setColspan(nationality, 3);
        }
        guardianInfo();
        applicantHistory();
        additionalInformation();
    }
    private void guardianInfo(){
        var layout = new VerticalLayout();
        H5 desc = new H5("GUARDIAN CONTACT INFORMATION");
        layout.addClassNames(LumoUtility.Border.ALL);
        GeneralForm guardianForm = new GeneralForm();
        Bingel guardian = bingel.getGuardian();
        guardianForm.viewGuardian();
        guardianForm.setBingel(guardian);
        guardianForm.setGoddo(guardian.getGoddo());
        guardianForm.setParent(guardian.getParent());
        
        layout.add(desc, guardianForm);
        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, desc);
        
        add(layout);
    }
    private void applicantHistory(){
        var layout = new VerticalLayout();
        layout.addClassNames(LumoUtility.Border.ALL);
        H5 desc = new H5("APPLICANT HISTORY");
        
        ApplicationForm applicationForm = new ApplicationForm();
        Applicant applicant = bingel.getApplicant() == null ? new Applicant() : bingel.getApplicant();
        applicationForm.setApplicant(applicant);
        applicationForm.viewApplication();
        applicationForm.setApplicant(bingel.getApplicant());
        layout.add(desc, applicationForm);
        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, desc);
        add(layout);
        
        setColspan(layout, 2);
    }
    private void additionalInformation(){
        var layout = new VerticalLayout();
        layout.addClassNames(LumoUtility.Border.ALL);
        H5 desc = new H5("ADDITIONAL INFORMATION");
        
        ApplicationForm applicationForm = new ApplicationForm();
        Applicant applicant = bingel.getApplicant() == null ? new Applicant() : bingel.getApplicant();
        applicationForm.setApplicant(applicant);
        applicationForm.viewAdditionalInfo();
        applicationForm.setApplicant(bingel.getApplicant());
        layout.add(desc, applicationForm);
        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, desc);
        add(layout);
    }
}