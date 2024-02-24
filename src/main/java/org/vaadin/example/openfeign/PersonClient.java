package org.vaadin.example.openfeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vaadin.example.model.Person;

@FeignClient(name = "person", url = "http://localhost", path = "/")
public interface PersonClient {

    @GetMapping("/p")
    public ResponseEntity<List<Person>> p(@RequestParam(required = false, name = "text") String text);
    @PostMapping("/d/{name}")
    public ResponseEntity<List<Person>> d(@PathVariable(name = "name") String name);
    

}