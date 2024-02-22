package org.vaadin.example.view;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.vaadin.example.model.Person;
import org.vaadin.example.openfeign.PersonClient;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import lombok.val;

@Route
public class MainView extends VerticalLayout {

    private static final long serialVersionUID = 2857094787956546845L;
	private TextField t = new TextField();
	private Grid<Person> g = new Grid<>();
	private PersonClient p;

    public MainView(final PersonClient p) {
    	System.out.println(p);
    	System.out.println(p.p(null));
    	this.p = p;
        t.setPlaceholder("Filter by name...");
        t.setClearButtonVisible(true);
        t.setValueChangeMode(ValueChangeMode.EAGER);
        val b = new Button("Search new person");
        b.addClickListener(e -> {
        	get();
        });
        val toolbar = new HorizontalLayout(t, b);
		g.addColumn(Person::getName).setHeader("Name");
		g.addColumn(o -> Integer.toString(o.getBirth())).setHeader("Year of birth");
		g.setSelectionMode(SelectionMode.SINGLE);
        val main = new HorizontalLayout(g);
        
        main.setSizeFull();
        get();
        add(toolbar, main);
    }

    void get() {
    	g.setItems(p.p(null).getBody());
    }
    
}