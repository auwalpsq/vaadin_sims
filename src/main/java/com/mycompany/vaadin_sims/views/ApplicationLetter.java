/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.views;

import com.mycompany.vaadin_sims.entities.Bingel;
import com.mycompany.vaadin_sims.entities.Goddo;
import com.mycompany.vaadin_sims.entities.Parent;
import com.mycompany.vaadin_sims.services.ApplicantId;
import com.mycompany.vaadin_sims.services.GuardianId;
import com.mycompany.vaadin_sims.services.SimsService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Auwal Usman
 */
@Route("application_letter")
public class ApplicationLetter extends VerticalLayout{
    private SimsService service;
    private Bingel bingel;
    private Bingel guardian;
    private Goddo goddo;
    private Parent parent;
    
    private SchoolInfoView schoolInfo = new SchoolInfoView();
    H3 application = new H3("APPLICATION FORM");
    
    public ApplicationLetter(SimsService service, @Autowired ApplicantId applicantId, @Autowired GuardianId guardianId){
        this.service = service;
        bingel = service.findBingelById(applicantId.getId()).get();
        guardian = bingel.getGuardian();
        goddo = guardian.getGoddo();
        parent = guardian.getParent();
        
        this.addClassNames("application-letter");
        addData();
    }
    
    public Div getPersonalData(){
        Div data = new Div();
        
        data.addClassNames(
                            LumoUtility.Display.INLINE_GRID,
                            LumoUtility.Grid.Column.COLUMNS_6,
                            LumoUtility.Border.ALL,
                            LumoUtility.AlignSelf.CENTER,
                            LumoUtility.Gap.XSMALL,
                            LumoUtility.Padding.SMALL
        );
        
        data.add(getCustomHeader("Applicant Personal Data"));
        
        data.add(getCustomLabel("Surname"), getCustomData(bingel.getLastName()),
                getCustomLabel("First Name"), getCustomData(bingel.getFirstName()),
                getCustomLabel("Other Names"), getCustomData(bingel.getOtherNames())
        );
        
        
        data.add(getCustomLabel("Gender"), getCustomData(bingel.getGender()),
                getCustomLabel("Date of Birth"), getCustomData(bingel.getBirthDate().toString()),
                getCustomLabel("Nationality"), getCustomData(bingel.getNationality())
        );
        
        Span address = getCustomData(goddo.getAddress());
        address.addClassNames(LumoUtility.Grid.Column.COLUMN_SPAN_5);
        
        data.add(getCustomLabel("Address"), address);
        
        data.add(getCustomHeader("Guardian Details"));
        
        Span fullName = getCustomData(guardian.getFirstName() + " " + guardian.getLastName() + " " + guardian.getOtherNames());
        fullName.addClassNames(LumoUtility.Grid.Column.COLUMN_SPAN_3);
        data.add(getCustomLabel("Full Name"), fullName, getCustomLabel("Occupation"), getCustomData(parent.getOccupation()));
        
        HorizontalLayout layoutOccupation = new HorizontalLayout();
        layoutOccupation.add(getCustomData(goddo.getEmail()), getCustomLabel("Email"));
        layoutOccupation.addClassNames(LumoUtility.Grid.Column.COLUMN_SPAN_4, LumoUtility.FlexDirection.ROW_REVERSE);
        
        data.add(getCustomLabel("Phone Number"), getCustomData(goddo.getPhone()), layoutOccupation);
        
        return data;
    }
    
    public void addData(){
        setAlignSelf(Alignment.CENTER, application);
        add(schoolInfo, application);
        add(getPersonalData());
    }
    private Span getCustomLabel(String text){
        Span span = new Span(text);
        span.addClassNames(LumoUtility.FontWeight.BOLD);

        return span;
    }
    private Span getCustomData(String text){
        Span span = new Span(text);
        
        return span;
    }
    private H4 getCustomHeader(String text){
        H4 header = new H4(text);
        
        header.addClassNames(LumoUtility.Grid.Column.COLUMN_SPAN_6,
                                    LumoUtility.TextAlignment.CENTER,
                                    LumoUtility.FontWeight.BOLD,
                                    LumoUtility.Margin.Bottom.MEDIUM
        );
        
        return header;
    }
}
