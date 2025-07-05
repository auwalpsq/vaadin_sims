package com.mycompany.vaadin_sims.views;

import com.mycompany.vaadin_sims.entities.SchoolInfo;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;

public class MainView extends AppLayout {
    
    public MainView() {
        DrawerToggle toggle = new DrawerToggle();
        
        H3 name = new H3(SchoolInfo.NAME.getData());
        H4 app = new H4(SchoolInfo.APP.getData());
        name.addClassNames("school-info");
        app.addClassNames("school-info");
        
        VerticalLayout schoolInfo = new VerticalLayout();
        schoolInfo.setAlignItems(FlexComponent.Alignment.CENTER);
        
        schoolInfo.add(name, app);
        schoolInfo.setSpacing(false);
        SideNav nav = getSideNav();
        
        Scroller scroller = new Scroller(nav);
        
        addToDrawer(scroller);
        addToNavbar(toggle, schoolInfo);
    }
    
    
    private SideNav getSideNav(){
        SideNav nav = new SideNav();
        
        nav.addItem(
                new SideNavItem("Guardian List", GuardianList.class, VaadinIcon.LIST.create()),
                new SideNavItem("Applicant List", ApplicantList.class, VaadinIcon.LIST.create())
        );
        
        return nav;
    }
}