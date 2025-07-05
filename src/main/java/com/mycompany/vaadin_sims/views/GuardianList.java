/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.views;

import com.mycompany.vaadin_sims.forms.GeneralForm;
import com.mycompany.vaadin_sims.entities.Bingel;
import com.mycompany.vaadin_sims.entities.TypeOfBingel;
import com.mycompany.vaadin_sims.services.GuardianId;
import com.mycompany.vaadin_sims.services.SimsService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author Auwal Usman
 */
@Route(value="", layout=MainView.class)
public class GuardianList extends VerticalLayout{
    private SimsService service;
    private GuardianId guardianId;
    
    private GeneralForm personalData;
    private Grid<Bingel> grid = new Grid<>(Bingel.class);
    
    private TextField filter = new TextField();
    public GuardianList(SimsService service, @Autowired GuardianId guardianId){
        this.service = service;
        this.guardianId = guardianId;
        
        configurePersonalData();
        configureGrid();
        configureFilter();
        this.add(getNewCandidate(), getMyContent());
        
        closeEditor();
        updateList();
    }
    
    private HorizontalLayout getNewCandidate(){
        HorizontalLayout layout = new HorizontalLayout();
        Button btNew = new Button("New Guardian", VaadinIcon.PLUS.create());
        layout.add(filter, btNew);
        layout.setWidthFull();
        filter.setWidthFull();
        filter.setPrefixComponent(VaadinIcon.FILTER.create());
        btNew.addClickListener(e -> addNewCandidate());
        
        return layout;
    }
    private void configureFilter(){
        filter.setPlaceholder("Filter by name...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(event -> updateList());
    }
    private void configurePersonalData(){
        personalData = new GeneralForm();
        personalData.newEntry();
        personalData.setWidth("25em");
        personalData.addListener(GeneralForm.SaveEvent.class, this::saveBingel);
        personalData.addListener(GeneralForm.DeleteEvent.class, this::deleteBingel);
        personalData.addListener(GeneralForm.CloseEvent.class, e -> closeEditor());
    }
    private void saveBingel(GeneralForm.SaveEvent event){
        service.saveBingel(event.getBingel());
        updateList();
        closeEditor();
    }
    private void deleteBingel(GeneralForm.DeleteEvent event){
        service.deleteBingel(event.getBingel());
        updateList();
        closeEditor();
    }
    private void configureGrid(){
        grid.setColumns("id", "firstName", "lastName", "type", "registrationDate");
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.addColumn(new ComponentRenderer<>(bingel -> {
            Button btOpen = new Button("Open Folder");
            btOpen.addClickListener(e -> {
                guardianId.setId(bingel.getId());
                UI.getCurrent().navigate("guardian_view");
            });
            return btOpen;
        })).setHeader("Folder");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(e -> editBingel(e.getValue()));
    }
    private void updateList(){
        grid.setItems(service.findAllBikkoi(filter.getValue(), TypeOfBingel.GUARDIAN.getType()));
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