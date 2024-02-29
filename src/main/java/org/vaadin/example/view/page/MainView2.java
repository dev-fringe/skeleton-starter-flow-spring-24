package org.vaadin.example.view.page;

import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.vaadin.example.client.PersonClient;
import org.vaadin.example.model.Person;
import org.vaadin.example.view.BasicLayout;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.History;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import lombok.SneakyThrows;
import lombok.val;

@SuppressWarnings("serial")
@Route(value = "/data", layout = BasicLayout.class)
@RouteAlias(value = "/data", layout = BasicLayout.class)
public class MainView2 extends VerticalLayout {

	TextField t = new TextField();
	Grid<Person> g = new Grid<>();
	PersonClient p;

    public MainView2(final PersonClient p) {
    	History history = UI.getCurrent().getPage().getHistory();
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
        Button button1 = new Button();
        button1.setText("Go back");
        button1.addClickListener(e-> history.back());
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
        add(new HorizontalLayout(t, b,upload,dialog, button, button1), main);
    }

    void get() {
    	g.setItems(p.p(t.getValue()).getBody());
    }
    
    TextField firstNameField = new TextField("First name");
    TextField lastNameField = new TextField("Last name");

    @SneakyThrows
    private  VerticalLayout createDialogLayout() {
//    	RichTextEditor rte = new RichTextEditor();
//    	rte.setMaxHeight("400px");// liecense
    	Document doc = Jsoup.connect("http://localhost/page1").get();
    	System.out.println(doc.toString());
    	Html html = new Html(doc.toString());
        VerticalLayout dialogLayout = new VerticalLayout(firstNameField,
                lastNameField, html);
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