/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.views;

import com.mycompany.vaadin_sims.entities.Applicant;
import com.mycompany.vaadin_sims.entities.Bingel;
import com.mycompany.vaadin_sims.entities.Goddo;
import com.mycompany.vaadin_sims.entities.Parent;
import com.mycompany.vaadin_sims.forms.ApplicationForm;
import com.mycompany.vaadin_sims.forms.GeneralForm;
import com.mycompany.vaadin_sims.forms.ProfileView;
import com.mycompany.vaadin_sims.services.ApplicantId;
import com.mycompany.vaadin_sims.services.GuardianId;
import com.mycompany.vaadin_sims.services.SimsService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Auwal Usman
 */
@Route(value="edit", layout=MainView.class)
public class Edit extends VerticalLayout{
    private SimsService service;
    
    private Bingel bingel;
    private Button btUpdate = new Button("Update Profile");
    
    GeneralForm generalForm;
    ApplicationForm applicationForm;
    ProfileView profileView;
    
    private ApplicantId applicantId;
    private GuardianId guardianId;
    
    private TabSheet tabSheet = new TabSheet();
    
    public Edit(SimsService service, @Autowired ApplicantId applicantId, @Autowired GuardianId guardianId){
        this.service = service;
        this.applicantId = applicantId;
        this.guardianId = guardianId;
    }
    private void configureBingelForm(){
        Applicant applicant = bingel.getApplicant() == null ? new Applicant() : bingel.getApplicant();
        
        profileView = new ProfileView();
        profileView.setBingel(bingel);
        profileView.setApplicant(applicant);
        profileView.viewApplicant(); 
        profileView.setBingel(bingel);
        
        generalForm = new GeneralForm();
        generalForm.generalDataApplicant();
        generalForm.addListener(GeneralForm.SaveEvent.class, this::saveBingelGeneral);
        generalForm.addListener(GeneralForm.DeleteEvent.class, this::deleteBingelGeneral);
        generalForm.addListener(GeneralForm.CloseEvent.class, event -> closeForm());
        generalForm.setBingel(bingel);
        generalForm.edit();
        
        applicationForm = new ApplicationForm();
        applicationForm.configureApplicationForm();
        applicationForm.addListener(ApplicationForm.SaveEvent.class, this::saveBingel);
        applicationForm.addListener(ApplicationForm.DeleteEvent.class, this::deleteBingel);
        applicationForm.addListener(ApplicationForm.CloseEvent.class, event -> closeForm());
        
        
        applicationForm.setApplicant(applicant);
        applicationForm.setBingel(bingel);
        
        if(bingel.getApplicant() == null){
            applicationForm.setDefaultValues();
        }else{
            applicationForm.setDefaultValues(applicant);
        }
        
        tabSheet.setSizeFull();
        tabSheet.add("Applicant profile", profileView);
        tabSheet.add("Applicant Personal Data", generalForm);
        tabSheet.add("Additional Data", applicationForm);
        
        add(tabSheet);
    }
    
    private void configureGuardianForm(){
        generalForm = new GeneralForm();
        generalForm.generalDataGuardian();
        generalForm.setBingel(bingel);
        generalForm.edit();
        generalForm.addListener(GeneralForm.UpdateEvent.class, this::update);
        add(generalForm);
    }
    private void update(GeneralForm.UpdateEvent event){
        bingel.setParent(event.getParent());
        bingel.setGoddo(event.getGoddo());
        
        service.saveBingel(event.getBingel());
        
        this.removeAll();
        editGuardian();
        
        Notification notification = getNotification("Updated Successfully");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }
    private void saveBingel(ApplicationForm.SaveEvent event){
        applicationForm.saveApplicant();
        service.saveBingel(event.getBingel());
        
        Notification notification = getNotification("Updated Successfully");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }
    private void saveBingelGeneral(GeneralForm.SaveEvent event){
        service.saveBingel(event.getBingel());
        
        Notification notification = getNotification("Updated Successfully");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }
    private void deleteBingel(GeneralForm.DeleteEvent event){
        Notification notification = getNotification("You CANNOT delete here");
        
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
    }
    private void deleteBingelGeneral(GeneralForm.DeleteEvent event){
        Notification notification = getNotification("You CANNOT delete here");
        
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
    }
    private void closeForm(){
        generalForm.setVisible(false);
        generalForm.setBingel(null);
        applicationForm.setVisible(false);
        applicationForm.setBingel(null);
        UI.getCurrent().navigate("guardian_view");
    }
    
    public Notification getNotification(String text){
        Notification notification = new Notification();
        
        Span span = new Span(text);
        span.addClassNames(LumoUtility.Width.FULL, LumoUtility.TextAlignment.CENTER);
        notification.add(span);
        notification.setPosition(Position.TOP_STRETCH);
        notification.setDuration(3000);
        
        notification.open();
        
        return notification;
    }
    
    private void edit(Long id){
        bingel = service.findBingelById(id).get();
        
        H4 type = new H4("THIS IS A " + bingel.getType().toUpperCase());
        type.addClassNames(LumoUtility.Width.FULL, LumoUtility.TextAlignment.CENTER, LumoUtility.TextColor.ERROR, LumoUtility.Background.WARNING);
        
        add(type);
    }
    public void editGuardian(){
        edit(guardianId.getId());
        configureGuardianForm();
        
        Goddo goddo = bingel.getGoddo() == null ? new Goddo() : bingel.getGoddo();
        Parent parent = bingel.getParent() == null ? new Parent() : bingel.getParent();
        
        generalForm.setGoddo(goddo);
        generalForm.setParent(parent);
    }
    public void editBingel(){
        edit(applicantId.getId());
        configureBingelForm();
    }
}