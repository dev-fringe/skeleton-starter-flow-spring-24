package org.vaadin.example;

import org.springframework.web.client.RestTemplate;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

//@Route
public class MainView2{ 
//extends VerticalLayout {
//	private static final long serialVersionUID = -989267332938477318L;
//
//	public MainView2(final RestTemplate t) {
//		Person[] p = t.getForObject("http://localhost:80/p", Person[].class);
//		Grid<Person> g = new Grid<>();
//		g.setItems(p);
//		g.addColumn(Person::getName).setHeader("Name");
//		g.addColumn(o -> Integer.toString(o.getBirth())).setHeader("Year of birth");
//		g.setSelectionMode(SelectionMode.SINGLE);
//		g.addItemClickListener(e -> Notification.show(("Clicked Item: " + e.getItem().getName())));
//		add(g);
//	}
//	add(new Button("Click me", e -> Notification.show(t.getForObject("http://localhost:80/hello", String.class))));
}
