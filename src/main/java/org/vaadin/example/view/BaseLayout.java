package org.vaadin.example.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * The main view is a top-level placeholder for other views.
 */
@Route("app-layout-basic")
public class BaseLayout extends AppLayout {

	private static final long serialVersionUID = 1L;

    public BaseLayout() {
        DrawerToggle toggle = new DrawerToggle();
        H1 title = new H1("App");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        SideNav nav = getSideNav();
        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);
        addToDrawer(scroller);
        addToNavbar(toggle, title);
    }
    private SideNav getSideNav() {
        SideNav sideNav = new SideNav();
        sideNav.addItem(new SideNavItem("crud", MainView.class,VaadinIcon.LIST.create()));
        sideNav.addItem(new SideNavItem("crud1", "/data",VaadinIcon.LIST.create()));
        sideNav.addItem(new SideNavItem("crud2", "/app3",VaadinIcon.LIST.create()));
        sideNav.addItem(new SideNavItem("crud3", "/app4",VaadinIcon.LIST.create()));
        sideNav.addItem(new SideNavItem("crud4", "/app5",VaadinIcon.LIST.create()));
               return sideNav;
    }
    
    @Override
    protected void afterNavigation() {
        super.afterNavigation();
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
