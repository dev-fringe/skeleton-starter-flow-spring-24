package org.vaadin.example.view;

import org.vaadin.example.model.Person;
import org.vaadin.example.openfeign.PersonClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import lombok.val;

@Route
@SuppressWarnings("serial")
public class MainView extends VerticalLayout {

	TextField t = new TextField();
	Grid<Person> g = new Grid<>();
	PersonClient p;

    public MainView(final PersonClient p) {
    	this.p = p;
        val b = new Button("Search new person");
        b.addClickListener(e -> {
        	get();
        });
		g.addColumn(Person::getName).setHeader("Name");
		g.addColumn(o -> Integer.toString(o.getBirth())).setHeader("Year of birth");
        val main = new HorizontalLayout(g);
        main.setSizeFull();
        get();
        add(new HorizontalLayout(t, b), main);
    }

    void get() {
    	g.setItems(p.p(t.getValue()).getBody());
    }
    
}