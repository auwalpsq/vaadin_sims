/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.views;

import com.mycompany.vaadin_sims.entities.Bingel;
import com.mycompany.vaadin_sims.entities.TypeOfBingel;
import com.mycompany.vaadin_sims.forms.FormBingel;
import com.mycompany.vaadin_sims.services.SimsService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 *
 * @author Auwal Usman
 */
@Route(value="parent_profile", layout=MainProfile.class)
public class ProfileGuardian extends VerticalLayout{
    private Bingel bingel;
    private Grid<Bingel> grid = new Grid<>(Bingel.class);
    private FormBingel form;
    SimsService service;
    
    public ProfileGuardian(SimsService service){
        this.service = service;
    }
    
    public void configureGrid(){
        grid.setColumns("id", "firstName", "lastName");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> {
            showEditor(event.getValue());
        });
        
        updateList();
    }
    public void configureForm(){
        form = new FormBingel();
        form.setWidth("25em");
        closeEditor();
        
        form.addListener(FormBingel.SaveEvent.class, this::saveBingel);
        form.addListener(FormBingel.CloseEvent.class, e -> closeEditor());
        form.addListener(FormBingel.DeleteEvent.class, this::deleteBingel);
    }
    public void saveBingel(FormBingel.SaveEvent event){
        service.saveBingel(event.getBingel());
        updateList();
        addNewChild();
    }
    public void deleteBingel(FormBingel.DeleteEvent event){
        service.deleteBingel(event.getBingel());
        updateList();
        addNewChild();
    }
    public void updateList(){
        grid.setItems(service.findBingelById(bingel.getId()).get().getBikkoi());
    }
    public void setParent(Bingel bingel){
        this.bingel = bingel;
        
        configureForm();
        configureGrid();
        
        configureNavBar();
        configureDrawer();
        
        add(addNewChild(), getMainContent());
    }
    public Component getMainContent(){
        HorizontalLayout content = new HorizontalLayout();
        content.setSizeFull();
        
        content.add(form, grid);
        
        return content;
    }
    
    public void closeEditor(){
        form.setBingel(null);
        grid.asSingleSelect().clear();
        form.setVisible(false);
    }
    public Component addNewChild(){
        HorizontalLayout content = new HorizontalLayout();
        
        form.setVisible(true);
        Bingel newBingel = new Bingel();
        newBingel.setBingel(bingel);
        newBingel.setType(TypeOfBingel.STUDENT.getType());
        form.setBingel(newBingel);
        
        Button btAddNew = new Button("New Child", VaadinIcon.PLUS.create(), event -> addNewChild());
        
        content.add(btAddNew);
        
        return content;
    }
    public void showEditor(Bingel bingel){
        form.setVisible(true);
        form.setBingel(bingel);
    }
    
    public void configureNavBar(){
        HorizontalLayout content = new HorizontalLayout();
        H4 fullname = new H4(bingel.getFirstName() + " " + bingel.getLastName() + " " + bingel.getOtherNames());
        content.setWidthFull();
        content.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        content.add(fullname);
        
        DrawerToggle toggle = new DrawerToggle();
        
        AppLayout parent = (AppLayout)this.getParent().get();
        
        parent.addToNavbar(toggle, content);
    }
    public void configureDrawer(){
        Button btGuardian = new Button("Guardian List", VaadinIcon.LIST.create(), event -> getUI().ifPresent(ui -> ui.navigate(GuardianList.class)));
        Button editProfile = new Button("Edit Profile", VaadinIcon.EDIT.create(),
                event -> getUI()
                        .ifPresent(ui -> ui.navigate(EditGuardian.class)
                        .ifPresent(edit -> edit.setBingel(bingel)))
        );
        
        AppLayout parent = (AppLayout)this.getParent().get();
        
        parent.addToDrawer(btGuardian, editProfile);
    }
}