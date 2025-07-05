/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.views;

import com.mycompany.vaadin_sims.entities.Bingel;
import com.mycompany.vaadin_sims.entities.TypeOfBingel;
import com.mycompany.vaadin_sims.forms.GeneralForm;
import com.mycompany.vaadin_sims.services.ApplicantId;
import com.mycompany.vaadin_sims.services.GuardianId;
import com.mycompany.vaadin_sims.services.SimsService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Auwal Usman
 */
@Route(value="guardian_view", layout=MainView.class)
public class ProfileGuardian extends VerticalLayout{
    private Bingel bingel;
    private Grid<Bingel> grid = new Grid<>(Bingel.class);
    private GeneralForm form;
    private ApplicantId applicantId;
    private GuardianId guardianId;
    private SimsService service;
    
    private TabSheet tabs = new TabSheet();
    
    public ProfileGuardian(SimsService service, @Autowired GuardianId guardianId, @Autowired ApplicantId applicantId){
        this.service = service;
        bingel = service.findBingelById(guardianId.getId()).get();
        this.applicantId = applicantId;
        this.guardianId = guardianId;
        
        configureGrid();
        configureForm();
        
        tabs.setSizeFull();
        tabs.add("Children", getChildrenTab());
        tabs.add("Edit Guardian", getEditGuardianTab());
        add(tabs);
    }
    
    public void configureGrid(){
        grid.setColumns("id", "firstName", "lastName");
        grid.addColumn(new ComponentRenderer<>(bingel -> {
            Anchor anchor = new Anchor("/application_letter", "Open Application Letter");
            anchor.getElement().setAttribute("target", "_blank");
            anchor.addFocusListener(e -> {applicantId.setId(bingel.getId());});
            return anchor;
            
            })).setHeader("Application Letter");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> {
            showEditor(event.getValue());
        });
        grid.addColumn(new ComponentRenderer<>(bingel -> {
            Button btMore = new Button("Open Folder");
            btMore.addClickListener(event -> {
                applicantId.setId(bingel.getId());
                getUI().ifPresent(ui -> ui.navigate(Edit.class).ifPresent(edit -> edit.editBingel()));
            });
            return btMore;
        })).setHeader("More");
        updateList();
    }
    public void configureForm(){
        form = new GeneralForm();
        form.newEntry();
        form.setWidth("25em");
        closeEditor();
        
        form.addListener(GeneralForm.SaveEvent.class, this::saveBingel);
        form.addListener(GeneralForm.CloseEvent.class, e -> closeEditor());
        form.addListener(GeneralForm.DeleteEvent.class, this::deleteBingel);
    }
    public void saveBingel(GeneralForm.SaveEvent event){
        service.saveBingel(event.getBingel());
        updateList();
        addNewChild();
    }
    public void deleteBingel(GeneralForm.DeleteEvent event){
        service.deleteBingel(event.getBingel());
        updateList();
        addNewChild();
    }
    public void updateList(){
        grid.setItems(service.findBingelById(bingel.getId()).get().getBikkoi());
    }
    
    public Component getChildrenTab(){
        VerticalLayout layout = new VerticalLayout();
        layout.add(getMenu(), getMainContent());
        
        return layout;
    }
    public Component getEditGuardianTab(){
        Edit edit = new Edit(service, applicantId, guardianId);
        edit.editGuardian();
        
        return edit;
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
        newBingel.setGuardian(bingel);
        newBingel.setType(TypeOfBingel.STUDENT.getType());
        showEditor(newBingel);
    }
    public Component getMenu(){
        HorizontalLayout content = new HorizontalLayout();
        Button btAddNew = new Button("New Child", VaadinIcon.PLUS.create(), event -> addNewChild());
        
        content.add(btAddNew);
        content.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        
        return content;
    }
    public void showEditor(Bingel bingel){
        form.setVisible(true);
        form.setBingel(bingel);
    }
}