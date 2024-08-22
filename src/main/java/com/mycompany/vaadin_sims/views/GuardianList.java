/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.views;

import com.mycompany.vaadin_sims.forms.FormBingel;
import com.mycompany.vaadin_sims.entities.Bingel;
import com.mycompany.vaadin_sims.entities.TypeOfBingel;
import com.mycompany.vaadin_sims.services.SimsService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
/**
 *
 * @author Auwal Usman
 */
@Route(value="", layout=MainView.class)
public class GuardianList extends VerticalLayout{
    private SimsService service;
    
    private FormBingel personalData;
    private Grid<Bingel> grid = new Grid<>(Bingel.class);
    private Button btNew = new Button("New Candidate", VaadinIcon.USER.create());
    
    public GuardianList(SimsService service){
        this.service = service;
        
        configurePersonalData();
        configureGrid();
        this.add(getNewCandidate(), getMyContent());
        
        closeEditor();
        updateList();
    }
    
    private FlexLayout getNewCandidate(){
        FlexLayout layout = new FlexLayout();
        layout.setFlexDirection(FlexLayout.FlexDirection.ROW_REVERSE);
        layout.setSizeFull();
        
        layout.add(btNew);
        
        btNew.addClickListener(e -> addNewCandidate());
        
        return layout;
    }
    private void configurePersonalData(){
        personalData = new FormBingel();
        personalData.setWidth("25em");
        personalData.addListener(FormBingel.SaveEvent.class, this::saveBingel);
        personalData.addListener(FormBingel.DeleteEvent.class, this::deleteBingel);
        personalData.addListener(FormBingel.CloseEvent.class, e -> closeEditor());
    }
    private void saveBingel(FormBingel.SaveEvent event){
        service.saveBingel(event.getBingel());
        updateList();
        closeEditor();
    }
    private void deleteBingel(FormBingel.DeleteEvent event){
        service.deleteBingel(event.getBingel());
        updateList();
        closeEditor();
    }
    private void configureGrid(){
        grid.setColumns("id", "firstName", "lastName", "type", "registrationDate");
        grid.addColumn(new ComponentRenderer<>(bingel -> {
            Button btOpen = new Button("Open Profile");
            btOpen.addClickListener(e -> {
                btOpen.getUI().ifPresent(ui -> ui.navigate(ProfileGuardian.class).ifPresent(profile -> profile.setParent(bingel)));
            });
            return btOpen;
        })).setHeader("Profile");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(e -> editBingel(e.getValue()));
    }
    private void updateList(){
        grid.setItems(service.findBingelByType(TypeOfBingel.GUARDIAN.getType()));
    }
    private void closeEditor(){
        personalData.setVisible(false);
        personalData.setBingel(null);
        grid.asSingleSelect().clear();
    }
    private Component getMyContent(){
        HorizontalLayout content = new HorizontalLayout();
        content.setSizeFull();
        content.add(grid, personalData);
        
        return content;
    }
    private void addNewCandidate(){
        personalData.setVisible(true);
        Bingel newBingel = new Bingel();
        newBingel.setType(TypeOfBingel.GUARDIAN.getType());
        personalData.setBingel(newBingel);
        grid.asSingleSelect().clear();
    }
    private void editBingel(Bingel bingel){
        if(bingel == null){
            closeEditor();
        }else{
            personalData.setVisible(true);
            personalData.setBingel(bingel);
        }
    }
}