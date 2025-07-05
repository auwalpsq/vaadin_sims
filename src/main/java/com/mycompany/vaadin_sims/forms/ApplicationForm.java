/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.forms;

import com.mycompany.vaadin_sims.entities.Applicant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.server.StreamResource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Auwal Usman
 */
public class ApplicationForm extends GeneralForm{
    protected Applicant applicant;
    
    protected RadioButtonGroup<String> permissionToTreat = new RadioButtonGroup<>("Permission to treat");
    
    protected RadioButtonGroup<String> specialNeed = new RadioButtonGroup<>("Does child have special need?");
    protected TextField specialNeedExplain = new TextField("Please explain");
    
    protected RadioButtonGroup<String> allergy = new RadioButtonGroup<>("Does child have an allergy?");
    protected TextField allergyExplain = new TextField("Please explain");
    protected TextField allergyPrevent = new TextField("What steps do parent take to prevent allergy attack");
    
    protected RadioButtonGroup<String> medication = new RadioButtonGroup<>("Is the child on regular medication");
    protected TextField medicationExplain = new TextField("Please explain");
    
    protected RadioButtonGroup<String> mix = new RadioButtonGroup<>("Does your child mix easily with others?");
    
    protected TextField fear = new TextField("How does your child express fear?");
    
    protected TextField upset = new TextField("How does your child act when upset?");
    
    protected TextField discipline = new TextField("What form of discipline do you use on child?");
    
    protected TextField reward = new TextField("How do you reward good behavior?");
    
    protected TextField school = new TextField("Previous/Present School");
    
    protected TextField previousClass = new TextField("Class");
    
    protected TextField language = new TextField("Languages spoken");
    
    protected TextField foodAllergy = new TextField("Does your child have any food allergies?");

    protected TextArea otherInformation = new TextArea("Any other information");
    protected Binder<Applicant> applicationBinder = new BeanValidationBinder<>(Applicant.class);
    
    protected DatePicker startDate = new DatePicker("Application start date");
    protected TimePicker startTime = new TimePicker("Application start time");
    protected DateTimePicker lastUpdated = new DateTimePicker("Last updated");
    
    protected String passportFile;
    
    protected Image passport = new Image();
    
    protected Upload uploadPassport;
    
    public void configureUploadPassport(){
        uploadPassport = new Upload(new Receiver(){
            private File file;

            @Override
            public OutputStream receiveUpload(String fileName, String mimeType){
                String type = fileName.substring(fileName.indexOf("."), fileName.length());
                if(passportFile != null){
                    String currentFileType = passportFile.substring(passportFile.indexOf("."), passportFile.length());
                    if(!passportFile.isEmpty() && !currentFileType.equals(type)){
                        File currentFile = new File(passportFile);
                        if(currentFile.exists()){
                            currentFile.delete();
                        }
                    }
                }
                passportFile = bingel.getId() + type;
                file = new File("src\\main\\resources\\META-INF\\resources\\images\\" + passportFile);
                
                try{    
                    return new FileOutputStream(file);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                return null;
            }
        });
        uploadPassport.addSucceededListener(event -> {
            StreamResource streamResource = new StreamResource(passportFile, () -> {
                try {
                    return new FileInputStream(new File("src\\main\\resources\\META-INF\\resources\\images\\" + passportFile));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ApplicationForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            });
                passport.setSrc(streamResource);
                passport.setVisible(true);
        });
    }
    public void configureApplicationForm(){
        applicationBinder.bindInstanceFields(this);
        configureUploadPassport();
        this.setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("500px", 2),
                new ResponsiveStep("1000px", 3)
        );
        
        permissionToTreat.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL, RadioGroupVariant.LUMO_HELPER_ABOVE_FIELD);
        permissionToTreat.setLabel("Permission to Treat");
        permissionToTreat.setItems("Yes, I give my consent", "No, I do not give my consent");
        permissionToTreat.setHelperText(    "In the event of an accident or emergency, "
                                            + "the school may have to administer first aid "
                                            + "treatement to the child. If the treatment requires "
                                            + "a physician's attention, the school will have the child "
                                            + "treated at their registered hospital and the parent will settle the medical bills."
        
                                        );
        permissionToTreat.setTooltipText("Note: Treatment at the hospital will only be done in an event that the parents and emergency contact cannot be reached");
        permissionToTreat.getTooltip().withPosition(Tooltip.TooltipPosition.START_BOTTOM);
        this.setColspan(permissionToTreat, 3);
       
        specialNeed.setItems("Yes", "No");
        allergy.setItems("Yes", "No");
        medication.setItems("Yes", "No");
        mix.setItems("Yes", "No");
        
        passport.setWidth("200px");
        passport.setHeight("200px");
        passport.setVisible(false);
        
        HorizontalLayout passportLayout = new HorizontalLayout();
        passportLayout.add(uploadPassport, passport);
        
        setColspan(passportLayout, 3);
        add(    passportLayout,school, previousClass, language,
                permissionToTreat, specialNeed, specialNeedExplain,
                mix, allergy, allergyExplain, allergyPrevent,
                medication, medicationExplain, fear, upset, discipline,
                reward, foodAllergy, otherInformation
        );
        
        configureButtons();
        specialNeed.addValueChangeListener(event -> {
            setDefaultDisable(event.getSource(), specialNeedExplain);
        });
        allergy.addValueChangeListener(event -> {
            setDefaultDisable(event.getSource(), allergyExplain);
            setDefaultDisable(event.getSource(), allergyPrevent);
        });
        medication.addValueChangeListener(event -> {
            setDefaultDisable(event.getSource(), medicationExplain);
        });
    }
    public void setApplicant(Applicant applicant){
        this.applicant = applicant;
        applicationBinder.readBean(applicant);
        passportFile = applicant == null ? "" : applicant.getPassportFile();
        
        if(passportFile == null || passportFile.isEmpty()){
            passport.setVisible(false);
        }else{
            StreamResource streamResource = new StreamResource(passportFile, () -> {
                try {
                    return new FileInputStream(new File("src\\main\\resources\\META-INF\\resources\\images\\" + passportFile));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ApplicationForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            });
                passport.setSrc(streamResource);
                passport.setVisible(true);
        }
    }
    public void saveApplicant(){
        try{
            applicationBinder.writeBean(applicant);
            applicant.setPassportFile(passportFile);
            bingel.setApplicant(applicant);
        }catch(ValidationException ex){
            ex.printStackTrace();
        }
    }
    public void setDefaultDisable(RadioButtonGroup<String> options, TextField helper){
        if(options.getValue().equals("Yes")){
            helper.setEnabled(true);
        }else if(options.getValue().equals("No")){
            helper.setValue("");
            helper.setEnabled(false);
        }
    }
    
    public void setDefaultValues(){
        permissionToTreat.setValue("No, I do not give my consent");
        specialNeed.setValue("No");
        allergy.setValue("No");
        medication.setValue("No");
        mix.setValue("No");
        
        specialNeedExplain.setEnabled(false);
        allergyExplain.setEnabled(false);
        allergyPrevent.setEnabled(false);
        medicationExplain.setEnabled(false);
    }
    public void setDefaultValues(Applicant applicant){
        specialNeedExplain.setEnabled(applicant.getSpecialNeed().equals("Yes"));
        allergyExplain.setEnabled(applicant.getAllergy().equals("Yes"));
        allergyPrevent.setEnabled(applicant.getAllergy().equals("Yes"));
        medicationExplain.setEnabled(applicant.getMedication().equals("Yes"));
    }
    public void viewApplication(){
        setResponsiveSteps( new ResponsiveStep("0", 1),
                            new ResponsiveStep("500px",2)
        );
        applicationBinder.bindInstanceFields(this);
        applicationBinder.setReadOnly(true);
        
        permissionToTreat.setItems(applicant.getPermissionToTreat());
        specialNeed.setItems(applicant.getSpecialNeed());
        allergy.setItems(applicant.getAllergy());
        medication.setItems(applicant.getMedication());
        mix.setItems(applicant.getMix());
        
        add(    permissionToTreat, medication, specialNeed, mix, specialNeedExplain, fear,
                allergy, upset, allergyExplain, discipline, allergyPrevent, reward, language
        );
        setColspan(language, 2);
    }
    public void viewAdditionalInfo(){
        applicationBinder.bindInstanceFields(this);
        applicationBinder.setReadOnly(true);
        
        add(school, previousClass, startDate, startTime, lastUpdated);
    }
}