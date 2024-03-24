package org.vaadin.example.view;
 
import org.vaadin.example.view.page.MainView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("layout")
public class BasicLayout extends AppLayout {

    public BasicLayout() {
        var t = new H1("App");
        t.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "0");
        var s = new Scroller(getSideNav());
        s.setClassName(LumoUtility.Padding.SMALL);
        addToDrawer(s);
        addToNavbar(new DrawerToggle(), t);
    }
    private SideNav getSideNav() {
        var n = new SideNav();
        n.addItem(new SideNavItem("crud", MainView.class,VaadinIcon.LIST.create()));
        n.addItem(new SideNavItem("crud1", "/data",VaadinIcon.LIST.create()));
        return n;
    }
}
