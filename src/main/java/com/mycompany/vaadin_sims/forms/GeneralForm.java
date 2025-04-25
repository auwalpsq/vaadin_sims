/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.forms;

import com.mycompany.vaadin_sims.entities.Bingel;
import com.mycompany.vaadin_sims.entities.Gender;
import com.mycompany.vaadin_sims.entities.TypeOfBingel;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

/**
 *
 * @author Auwal Usman
 */
public class FormBingel extends FormLayout{
    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private TextField otherNames = new TextField("Other Names");
    
    private RadioButtonGroup<String> gender = new RadioButtonGroup<>();
    protected RadioButtonGroup<String> type = new RadioButtonGroup<>();
    
    private Binder<Bingel> binder = new BeanValidationBinder<>(Bingel.class);
    
    private Bingel bingel;
    
    Button btSave = new Button("Save");
    Button btDelete = new Button("Delete");
    Button btClose = new Button("Close");
    
    public FormBingel(){
        this.setResponsiveSteps(new ResponsiveStep("0", 1));
        binder.bindInstanceFields(this);
        
        gender.setItems(Gender.MALE.getGender(), Gender.FEMALE.getGender());
        gender.setLabel("Gender");
        
        type.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        type.setItems(TypeOfBingel.STUDENT.getType(), TypeOfBingel.GUARDIAN.getType(), TypeOfBingel.STAFF.getType());
        type.setReadOnly(true);
        
        type.setLabel("TYPE OF CANDIDATE");
        type.getStyle().set("background-color", "gold");
        
        add(type, firstName, lastName, otherNames, gender);
        configureButtons();
        setColspan(otherNames, 2);
        setColspan(gender, 2);
    }
    
    private void configureButtons(){
        HorizontalLayout layout = new HorizontalLayout();
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.add(btSave, btDelete, btClose);
        
        btSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btDelete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        
        btSave.addClickShortcut(Key.ENTER);
        btClose.addClickShortcut(Key.ESCAPE);
        
        add(layout);
        setColspan(layout, 2);
        
        btSave.addClickListener(event -> validateAndSave());
        btDelete.addClickListener(event -> fireEvent(new DeleteEvent(this, bingel)));
        btClose.addClickListener(event -> fireEvent(new CloseEvent(this)));
        
        binder.addStatusChangeListener(e -> btSave.setEnabled(binder.isValid()));
    }
    
    private void validateAndSave(){
        try{
            binder.writeBean(bingel);
            fireEvent(new SaveEvent(this, bingel));
        }catch(ValidationException e){
            e.printStackTrace();
        }
    }
    public void setBingel(Bingel bingel){
        this.bingel = bingel;
        binder.readBean(bingel);
    }
    public static abstract class BingelFormEvent extends ComponentEvent<FormBingel>{
        private Bingel bingel;
        
        protected BingelFormEvent(FormBingel source, Bingel bingel){
            super(source, false);
            this.bingel = bingel;
        }
        
        public Bingel getBingel(){return bingel;}
    }
    public static class SaveEvent extends BingelFormEvent{
        SaveEvent(FormBingel source, Bingel bingel){
            super(source, bingel);
        }
    }
    public static class DeleteEvent extends BingelFormEvent{
        DeleteEvent(FormBingel source, Bingel bingel){
            super(source, bingel);
        }
    }
    public static class CloseEvent extends BingelFormEvent{
        CloseEvent(FormBingel source){
            super(source, null);
        }
    }
    
    @Override
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener){
        return getEventBus().addListener(eventType, listener);
    } 
    
    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener){
        return addListener(SaveEvent.class, listener);
    }
    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener){
        return addListener(DeleteEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener){
        return addListener(CloseEvent.class, listener);
    }
}