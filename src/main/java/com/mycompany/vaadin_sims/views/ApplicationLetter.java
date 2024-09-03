/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.views;

import com.mycompany.vaadin_sims.entities.Bingel;
import com.mycompany.vaadin_sims.entities.Goddo;
import com.mycompany.vaadin_sims.services.SimsService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 *
 * @author Auwal Usman
 */
@Route("application_letter")
public class ApplicationLetter extends VerticalLayout{
    private SimsService service;
    static Bingel bingel;
    private Bingel guardian;
    private Goddo goddo;
    
    private SchoolInfoView schoolInfo = new SchoolInfoView();
    H5 application = new H5("APPLICATION FORM");
    
    public ApplicationLetter(SimsService service){
        this.service = service;
        //guardian = service.findGuardian(bingel.getParent());
        goddo = service.findGoddo(bingel.getParent());
        addData();
    }
    
    public Div getPersonalData(){
        Div data = new Div();
        
        data.addClassNames(
                            LumoUtility.Display.INLINE_GRID,
                            LumoUtility.Grid.Column.COLUMNS_6,
                            LumoUtility.Border.ALL,
                            LumoUtility.AlignSelf.CENTER
        );
        
        Span lblSurname = new Span("Surname:");
        lblSurname.addClassNames(LumoUtility.FontWeight.BOLD);
        Span surname = new Span(bingel.getLastName());
        
        Span lblFirstName = new Span("First Name:");
        lblFirstName.addClassNames(LumoUtility.FontWeight.BOLD);
        Span firstName = new Span(bingel.getFirstName());
        
        Span lblOtherNames = new Span("Other Names:");
        lblOtherNames.addClassNames(LumoUtility.FontWeight.BOLD);
        Span otherNames = new Span(bingel.getOtherNames());
        
        data.add(lblSurname, surname, lblFirstName, firstName, lblOtherNames, otherNames);
        
        Span lblGender = new Span("Gender");
        lblGender.addClassNames(LumoUtility.FontWeight.BOLD);
        Span gender = new Span(bingel.getGender());
        data.add(lblGender, gender);
        
        Span lblAddress = new Span("Address");
        lblAddress.addClassNames(LumoUtility.FontWeight.BOLD, LumoUtility.Background.ERROR_10);
        Span address = new Span(goddo.getAddress());
        address.addClassNames(LumoUtility.Grid.Column.COLUMN_SPAN_5, LumoUtility.Background.ERROR);
        
        data.add(lblAddress, address);
        return data;
    }
    
    public void addData(){
        this.removeAll();
        setAlignSelf(Alignment.CENTER, application);
        add(schoolInfo, application);
        add(getPersonalData());
//        
//        UI.setCurrent(this.getUI().get());
//        UI.getCurrent().getPage().fetchCurrentURL(url -> {
//            Notification.show(url.toString(), 3000, Notification.Position.TOP_CENTER);
//        });
        //UI.getCurrent().getPage().executeJs("window.open(location.href, \"_blank\", \"\");");
        //UI.getCurrent().getPage().executeJs("alert(location.href);");
    }
   
}
