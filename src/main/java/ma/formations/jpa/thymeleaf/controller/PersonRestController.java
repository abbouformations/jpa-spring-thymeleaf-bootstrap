package ma.formations.jpa.thymeleaf.controller;

import lombok.AllArgsConstructor;
import ma.formations.jpa.thymeleaf.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/persons")
public class PersonRestController {
    private IService service;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Person deleted successfully");
    }
}
