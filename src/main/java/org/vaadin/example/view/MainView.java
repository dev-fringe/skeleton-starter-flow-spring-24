package org.vaadin.example.view;

import java.io.InputStream;

import org.vaadin.example.model.Person;
import org.vaadin.example.openfeign.PersonClient;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;

import lombok.val;

@Route
@SuppressWarnings("serial")
public class MainView extends VerticalLayout {

	TextField t = new TextField();
	Grid<Person> g = new Grid<>();
	PersonClient p;

    public MainView(final PersonClient p) {
    	MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
    	Upload upload = new Upload(buffer);
    	upload.addSucceededListener(event -> {
    	    String fileName = event.getFileName();
    	    InputStream inputStream = buffer.getInputStream(fileName);
    	    System.out.println(fileName);
    	});
    	
    	this.p = p;
        val b = new Button("Search new person");
        b.addClickListener(e -> {
        	get();
        });
        Dialog dialog = new Dialog();

        dialog.setHeaderTitle("New employee");

        VerticalLayout dialogLayout = createDialogLayout();
        dialog.add(dialogLayout);

        Button saveButton = createSaveButton(dialog);
        Button cancelButton = new Button("Cancel", e -> dialog.close());
        Button deleteButton = new Button("Delete", e -> {
            System.out.println(firstNameField.getValue());
            g.setItems(p.d(lastNameField.getValue()).getBody());
        	dialog.close();});
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        dialog.getFooter().add(cancelButton);
        dialog.getFooter().add(saveButton);
        dialog.getFooter().add(deleteButton);

        Button button = new Button("Show dialog", e -> dialog.open());

		g.addColumn(Person::getName).setSortable(true).setHeader("Name");
		g.addColumn(o -> Integer.toString(o.getBirth())).setSortable(true).setHeader("Year of birth");
		g.addItemDoubleClickListener(e ->{
			firstNameField.setValue(e.getItem().getName());
			lastNameField.setValue(String.valueOf(e.getItem().getBirth()));
			dialog.open();
		});
        val main = new HorizontalLayout(g);
        main.setSizeFull();
        get();
        add(new HorizontalLayout(t, b,upload,dialog, button), main);
    }

    void get() {
    	g.setItems(p.p(t.getValue()).getBody());
    }
    
    TextField firstNameField = new TextField("First name");
    TextField lastNameField = new TextField("Last name");

    private  VerticalLayout createDialogLayout() {
        VerticalLayout dialogLayout = new VerticalLayout(firstNameField,
                lastNameField);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");
        return dialogLayout;
    }

    private  Button createSaveButton(Dialog dialog) {
        Button saveButton = new Button("Add", e -> {dialog.close();
        System.out.println(firstNameField.getValue());
        	});
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return saveButton;
    }
}