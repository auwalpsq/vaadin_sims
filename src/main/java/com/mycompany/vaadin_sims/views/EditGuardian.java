/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.views;

import com.mycompany.vaadin_sims.forms.FormGoddo;
import com.mycompany.vaadin_sims.forms.FormParent;
import com.mycompany.vaadin_sims.entities.Bingel;
import com.mycompany.vaadin_sims.entities.Goddo;
import com.mycompany.vaadin_sims.entities.Parent;
import com.mycompany.vaadin_sims.services.SimsService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 *
 * @author Auwal Usman
 */
@Route(value="edit_guardian", layout=MainView.class)
public class EditGuardian extends VerticalLayout{
    private SimsService service;
    private Bingel bingel;
    private Button btUpdate = new Button("Update Profile");
    
    public EditGuardian(SimsService service){
        this.service = service;
        
        btUpdate.addClassNames(LumoUtility.AlignSelf.CENTER);
        btUpdate.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
    }
    public void setBingel(Bingel bingel){
        this.bingel = bingel;
        add(getMenu());
        add(editProfile(bingel));
    }
    public VerticalLayout editProfile(Bingel bingel){
        VerticalLayout content = new VerticalLayout();
        content.addClassName(LumoUtility.Background.PRIMARY_10);
        
        BasicData basicData;
        FormGoddo formGoddo;
        FormParent formParent;
        
        Goddo goddo = service.findBingelGoddo(bingel);
        Parent parent = service.findBingelParent(bingel);
        
        basicData = new BasicData(bingel);
        
        formGoddo = new FormGoddo();
        formGoddo.setGoddo(goddo);
        
        formParent = new FormParent();
        formParent.setTheParent(parent);
        
        content.add(basicData,formGoddo,formParent,btUpdate);
        btUpdate.addClickListener(event -> {
            formGoddo.getGoddo().setBingel(bingel);
            formParent.getTheParent().setBingel(bingel);
            
            service.saveGoddo(formGoddo.getGoddo());
            service.saveParent(formParent.getTheParent());
            
            Notification notification = Notification.show("Profile Updated", 5000, Notification.Position.TOP_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
        
        return content;
    }
    public Component getMenu(){
        HorizontalLayout menuBar = new HorizontalLayout();
        
        H3 title = new H3(bingel.getFirstName() + " " + bingel.getLastName() + " " + bingel.getOtherNames());
        title.addClassNames(LumoUtility.TextAlignment.CENTER, LumoUtility.Width.FULL);
        
        Button btView = new Button("View Guardian",
                event -> getUI().ifPresent(
                        ui -> ui.navigate(ProfileGuardian.class)
                        .ifPresent(profile -> profile.setParent(bingel))
                )
        );
        menuBar.add(title,btView);
        menuBar.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        return menuBar;
    }
}
