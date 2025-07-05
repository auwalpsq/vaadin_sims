/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.forms;

import com.mycompany.vaadin_sims.entities.Bingel;
import com.mycompany.vaadin_sims.entities.Gender;
import com.mycompany.vaadin_sims.entities.Goddo;
import com.mycompany.vaadin_sims.entities.Parent;
import com.mycompany.vaadin_sims.entities.TypeOfBingel;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

/**
 *
 * @author Auwal Usman
 */
public class GeneralForm extends FormLayout{
    protected TextField firstName = new TextField("First Name");
    protected TextField lastName = new TextField("Last Name");
    protected TextField otherNames = new TextField("Other Names");
    protected RadioButtonGroup<String> gender = new RadioButtonGroup<>("Gender");
    protected RadioButtonGroup<String> type = new RadioButtonGroup<>("Type");
    protected DatePicker birthDate = new DatePicker("Date of Birth");
    protected RadioButtonGroup<String> religion = new RadioButtonGroup<>("Religion");
    protected RadioButtonGroup<String> nationality = new RadioButtonGroup<>("Nationality");
    protected Binder<Bingel> binder = new BeanValidationBinder<>(Bingel.class);
    
    protected TextField address = new TextField("Address");
    protected EmailField email = new EmailField("Email");
    protected TextField phone = new TextField("Phone");
    protected Binder<Goddo> binderGoddo = new BeanValidationBinder<>(Goddo.class);
    
    protected Binder<Parent> binderParent = new BeanValidationBinder<>(Parent.class);
    protected TextField occupation = new TextField("Occupation");
    protected TextField placeOfWork = new TextField("Place of Work");
    
    protected Bingel bingel;
    protected Goddo goddo;
    protected Parent parent;
    
    protected HorizontalLayout guardianLayout = new HorizontalLayout();
    
    protected Button btSave = new Button("Save");
    protected Button btUpdate = new Button("Update");
    protected Button btDelete = new Button("Delete");
    protected Button btClose = new Button("Close");
    
    public void edit(){
        firstName.setReadOnly(true);
        lastName.setReadOnly(true);
        otherNames.setReadOnly(true);
        gender.setReadOnly(true);
        type.setItems(bingel.getType());
        type.setValue(bingel.getType());
        type.setReadOnly(true);
        gender.setItems(bingel.getGender());
        gender.setValue(bingel.getGender());
        gender.setReadOnly(true);
    }
    public void newEntry(){
        add(type, firstName, lastName, otherNames, gender);
        
        this.setResponsiveSteps(new ResponsiveStep("0", 1));
        binder.bindInstanceFields(this);
        
        gender.setItems(Gender.MALE.getGender(), Gender.FEMALE.getGender());
        gender.setLabel("Gender");
        
        type.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        type.setItems(TypeOfBingel.STUDENT.getType(), TypeOfBingel.GUARDIAN.getType(), TypeOfBingel.STAFF.getType());
        type.setReadOnly(true);
        
        type.setLabel("TYPE OF CANDIDATE");
        type.getStyle().set("background-color", "gold");
        
        configureButtons();
    }
    
    public void generalDataGuardian(){
        this.setResponsiveSteps(new ResponsiveStep("0", 1));
        add(guardianLayout);
        binder.bindInstanceFields(this);
        VerticalLayout generalData = new VerticalLayout();
        generalData.add(type, firstName, lastName, otherNames, gender);
        guardianLayout.add(generalData);
        
        generalDataGoddo();
        configureUpdate();
    }
    
    public void generalDataApplicant(){
        this.setResponsiveSteps(
                    new ResponsiveStep("0", 1),
                    new ResponsiveStep("500px", 2),
                    new ResponsiveStep("1000px", 3)
                );
        binder.bindInstanceFields(this);
        
        add(type, firstName, lastName, otherNames, gender,
                        birthDate, religion, nationality);
        
        religion.setItems("Islam", "Christianity");
        religion.setLabel("Religion");
        
        nationality.setItems("Nigerian", "Non Nigerian");
        nationality.setLabel("Nationality");
        
        this.setColspan(type, 3);
        this.setColspan(gender, 3);
        this.setColspan(birthDate, 3);
        this.setColspan(religion, 3);
        this.setColspan(nationality, 3);
        
        configureButtons();
    }
    public void viewGuardian(){
        this.setResponsiveSteps(new ResponsiveStep("0", 1));
        binderGoddo.bindInstanceFields(this);
        binderParent.bindInstanceFields(this);
        binderGoddo.setReadOnly(true);
        binderParent.setReadOnly(true);
        add(address, email, phone, occupation, placeOfWork);
    }
    public void generalDataGoddo(){
        binderGoddo.bindInstanceFields(this);
        binderParent.bindInstanceFields(this);
        VerticalLayout goddoData = new VerticalLayout();
        address.setSizeFull();
        email.setSizeFull();
        phone.setSizeFull();
        occupation.setSizeFull();
        placeOfWork.setSizeFull();
        goddoData.add(address, email, phone, occupation, placeOfWork);
        
        guardianLayout.add(goddoData);
        add(btUpdate);
    }
    
    protected void configureButtons(){
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
        //btDelete.addClickListener(event -> fireEvent(new DeleteEvent(this, bingel)));
        btDelete.addClickListener(event -> {
            ConfirmDialog dialog = new ConfirmDialog();
            Button btWarning = new Button("Warning: Deleting " + bingel.getType());
            btWarning.addThemeVariants(ButtonVariant.LUMO_ERROR);
            dialog.setHeader(btWarning);
            
            dialog.setText(new Html(
                                    "<p>Deleting " +bingel.getFirstName() + " " + bingel.getLastName() + " " + bingel.getOtherNames()
                                    + "<br>will cause all records depending on this person to be deleted "
                                    + "<br>The action cannot be reverse</p>"
                            )
            );
            dialog.setConfirmText("Confirm Delete");
            dialog.addConfirmListener(e -> fireEvent(new DeleteEvent(this, bingel)));
            dialog.setConfirmButtonTheme("error primary");
            
            dialog.setCancelable(true);
            dialog.addCancelListener(e -> dialog.close());
            
            dialog.open();
        });
        btClose.addClickListener(event -> fireEvent(new CloseEvent(this)));
        
        binder.addStatusChangeListener(e -> btSave.setEnabled(binder.isValid()));
    }
    public void configureUpdate(){
        btUpdate.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        
        btUpdate.addClickShortcut(Key.ENTER);
        
        btUpdate.addClickListener(event -> validateAndUpdate());
    }
    protected void validateAndSave(){
        try{
            binder.writeBean(bingel);
            fireEvent(new SaveEvent(this, bingel));
        }catch(ValidationException e){
            e.printStackTrace();
        }
    }
    private void validateAndUpdate(){
        try{
            binder.writeBean(bingel);
            binderGoddo.writeBean(goddo);
            binderParent.writeBean(parent);
            fireEvent(new UpdateEvent(this, bingel, goddo, parent));
        }catch(ValidationException e){
            e.printStackTrace();
        }
    }
    public Bingel getBingel(){return bingel;}
    public void setBingel(Bingel bingel){
        this.bingel = bingel;
        binder.readBean(bingel);
    }
    public void setGoddo(Goddo goddo){
        this.goddo = goddo;
        binderGoddo.readBean(goddo);
    }
    public void setParent(Parent parent){
        this.parent = parent;
        binderParent.readBean(parent);
    }
    public static abstract class BingelFormEvent extends ComponentEvent<GeneralForm>{
        private Bingel bingel;
        private Goddo goddo;
        private Parent parent;
        
        protected BingelFormEvent(GeneralForm source, Bingel bingel){
            super(source, false);
            this.bingel = bingel;
        }
        protected BingelFormEvent(GeneralForm source, Bingel bingel, Goddo goddo, Parent parent){
            super(source, false);
            this.bingel = bingel;
            this.goddo = goddo;
            this.parent = parent;
        }
        public Bingel getBingel(){return bingel;}
        public Goddo getGoddo(){return goddo;}
        public Parent getParent(){return parent;}
        
        public void setBingel(Bingel bingel){this.bingel = bingel;}
        public void setParent(Parent parent){this.parent = parent;}
        public void setGoddo(Goddo goddo){this.goddo = goddo;}
    }
    public static class SaveEvent extends BingelFormEvent{
        SaveEvent(GeneralForm source, Bingel bingel){
            super(source, bingel);
        }
    }
    public static class DeleteEvent extends BingelFormEvent{
        DeleteEvent(GeneralForm source, Bingel bingel){
            super(source, bingel);
        }
    }
    public static class CloseEvent extends BingelFormEvent{
        CloseEvent(GeneralForm source){
            super(source, null);
        }
    }
    public static class UpdateEvent extends BingelFormEvent{
        UpdateEvent(GeneralForm source, Bingel bingel, Goddo goddo, Parent parent){
            super(source, bingel, goddo, parent);
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