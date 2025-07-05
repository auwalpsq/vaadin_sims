/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.views;

import com.mycompany.vaadin_sims.entities.Bingel;
import com.mycompany.vaadin_sims.entities.TypeOfBingel;
import com.mycompany.vaadin_sims.services.SimsService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

/**
 *
 * @author Auwal Usman
 */
@Route(value = "applicants", layout=MainView.class)
public class ApplicantList extends VerticalLayout{
    private SimsService service;
    
    private Grid<Bingel> applicants = new Grid<>(Bingel.class);
    
    private TextField filter = new TextField();
    
    public ApplicantList(SimsService service){
        this.service = service;
        
        configureFilter();
        configureApplicants();
        
        add(filter, applicants);
    }
    
    private void configureApplicants(){
        applicants.setColumns("id", "firstName", "lastName", "otherNames");
        applicants.addColumn(bingel -> bingel.getApplicant().getStartDate()).setHeader("Date");
        applicants.addColumn(bingel -> bingel.getApplicant().getStartTime()).setHeader("Time");
        applicants.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        applicants.getColumns().forEach(col -> col.setAutoWidth(true));
        
        updateApplicants();
    }
    
    private void updateApplicants(){
        applicants.setItems(service.findAllBikkoi(filter.getValue(), TypeOfBingel.STUDENT.getType()));
    }
    private void configureFilter(){
        filter.setPlaceholder("filter by name");
        filter.setSizeFull();
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(event -> updateApplicants());
        filter.setPrefixComponent(VaadinIcon.FILTER.create());
    }
}
