package com.mycompany.vaadin_sims.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainView extends AppLayout {
    
    public MainView() {
        DrawerToggle toggle = new DrawerToggle();
        
        H1 title = new H1("Student Information Management System");
       //title.getStyle().set("font-size", "var(--lumo-font-size-1)").set("margin", "0").set("background-color", "gold");
        title.setWidthFull();
        title.addClassNames(LumoUtility.TextAlignment.CENTER);
        
        SideNav nav = getSideNav();
        
        Scroller scroller = new Scroller(nav);
        
        addToDrawer(scroller);
        addToNavbar(toggle, title);
    }
    
    
    private SideNav getSideNav(){
        SideNav nav = new SideNav();
        
        nav.addItem(new SideNavItem("Guardian List", GuardianList.class, VaadinIcon.LIST.create())
                    
        );
        
        return nav;
    }
}
