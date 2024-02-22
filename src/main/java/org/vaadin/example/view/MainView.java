package org.vaadin.example.view;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;
import org.vaadin.example.model.Person;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Route
@Slf4j
public class MainView extends VerticalLayout {

    private static final long serialVersionUID = 2857094787956546845L;
	private TextField t = new TextField();
	private Grid<Person> g = new Grid<>();
	private RestTemplate r;

    public MainView(final RestTemplate r) {
    	log.info("test");
    	this.r = r;
    	
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
    	log.info("test start");
        get();
    	log.info("test4");

        add(toolbar, main);
    }

    @Async void get() {
    	log.info("test2");
    	g.setItems(r.getForObject("http://localhost:80/p?text=" + t.getValue(), Person[].class));
    	log.info("test3");
    }
    
}