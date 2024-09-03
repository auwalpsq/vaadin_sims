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
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

/**
 *
 * @author Auwal Usman
 */
@Route(value="guardian_view", layout=MainView.class)
public class ProfileGuardian extends VerticalLayout{
    private static Bingel bingel;
    private Grid<Bingel> grid = new Grid<>(Bingel.class);
    private FormBingel form;
    SimsService service;
    
    public ProfileGuardian(SimsService service){
        this.service = service;
        
        if(bingel != null){
            addData();
        }
    }
    
    public void configureGrid(){
        grid.setColumns("id", "firstName", "lastName");
        grid.addColumn(new ComponentRenderer<>(bingel -> {
            Anchor anchor = new Anchor("/application_letter", "Open Application Letter");
            anchor.getElement().setAttribute("target", "_blank");
            anchor.addFocusListener(event -> {
                ApplicationLetter.bingel = bingel;
            });
            return anchor;
            })).setHeader("Application Letter");
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
        addData();
    }
    public void addData(){
        this.removeAll();
        configureGrid();
        configureForm();
        
        add(getMenu(), getMainContent());
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
    public void addNewChild(){
        Bingel newBingel = new Bingel();
        newBingel.setParent(bingel);
        newBingel.setType(TypeOfBingel.STUDENT.getType());
        showEditor(newBingel);
    }
    public Component getMenu(){
        HorizontalLayout content = new HorizontalLayout();
        
        H3 fullName = new H3(bingel.getFirstName() + " " + bingel.getLastName() + " " + bingel.getOtherNames());
        Button btAddNew = new Button("New Child", VaadinIcon.PLUS.create(), event -> addNewChild());
        Button editProfile = new Button("Edit Profile", VaadinIcon.EDIT.create(),
                event -> getUI()
                        .ifPresent(ui -> ui.navigate(EditGuardian.class)
                            .ifPresent(edit -> edit.setBingel(bingel))
                        )
        );
        content.add(fullName, btAddNew, editProfile);
        content.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        
        return content;
    }
    public void showEditor(Bingel bingel){
        form.setVisible(true);
        form.setBingel(bingel);
    }
}