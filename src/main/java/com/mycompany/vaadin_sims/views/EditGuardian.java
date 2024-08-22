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
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 *
 * @author Auwal Usman
 */
@Route(value="parent", layout=MainProfile.class)
public class EditGuardian extends VerticalLayout{
    private Long parentId;
    private SimsService service;
    private Bingel bingel;
    private BasicData basicData;
    private FormGoddo formGoddo;
    private FormParent formParent;
    
    private Button btUpdate = new Button("Update Profile");
    
    public EditGuardian(SimsService service){
        this.service = service;
        this.addClassName(LumoUtility.Background.PRIMARY_10);
        
        btUpdate.addClassNames(LumoUtility.AlignSelf.CENTER);
        btUpdate.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
    }
    public void setBingel(Bingel bingel){
        this.bingel = bingel;
        editProfile(bingel);
    }
    public void editProfile(Bingel bingel){
        
        Goddo goddo = service.findBingelGoddo(bingel);
        Parent parent = service.findBingelParent(bingel);
        
        basicData = new BasicData(bingel);
        
        formGoddo = new FormGoddo();
        formGoddo.setGoddo(goddo);
        
        formParent = new FormParent();
        formParent.setTheParent(parent);
        
        H3 title = new H3(bingel.getType() + " Profile");
        title.addClassNames(LumoUtility.TextAlignment.CENTER, LumoUtility.Width.FULL);
        
        add(title,basicData);
        add(formGoddo);
        add(formParent);
        add(btUpdate);
        
        btUpdate.addClickListener(event -> {
            formGoddo.getGoddo().setBingel(bingel);
            formParent.getTheParent().setBingel(bingel);
            
            service.saveGoddo(formGoddo.getGoddo());
            service.saveParent(formParent.getTheParent());
            
            Notification notification = Notification.show("Profile Updated", 5000, Notification.Position.TOP_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
    }
}
