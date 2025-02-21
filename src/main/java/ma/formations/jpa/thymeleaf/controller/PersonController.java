package ma.formations.jpa.thymeleaf.controller;

import lombok.AllArgsConstructor;
import ma.formations.jpa.thymeleaf.dtos.PersonDto;
import ma.formations.jpa.thymeleaf.service.IService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class PersonController {
    private IService service;

    @GetMapping(value = {"/", "/persons"})
    public String getAll(Model model, @Param("keyword") String keyword) {
        try {
            List<PersonDto> persons = null;
            if (keyword == null) {
                persons = service.findAll();
            } else {
                persons = service.findByFirstnameContainingIgnoreCase(keyword);
                model.addAttribute("keyword", keyword);
            }
            model.addAttribute("persons", persons);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "persons";
    }

    @GetMapping("/persons/new")
    public String addPerson(Model model) {
        PersonDto person = new PersonDto();
        person.setMarried(true);
        model.addAttribute("person", person);
        model.addAttribute("pageTitle", "Create new Person");
        return "person_form";
    }

    @PostMapping("/persons/save")
    public String savePerson(PersonDto person, RedirectAttributes redirectAttributes) {
        try {
            service.save(person);
            redirectAttributes.addFlashAttribute("message", "The Person has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/persons";
    }

    @GetMapping("/persons/{id}")
    public String editPerson(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            PersonDto person = service.findById(id);
            model.addAttribute("person", person);
            model.addAttribute("pageTitle", "Edit Person (ID: " + id + ")");
            return "person_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/persons";
        }
    }

    @GetMapping("/persons/{id}/married/{status}")
    public String updatePersonStatus(@PathVariable("id") Long id, @PathVariable("status") boolean married, RedirectAttributes redirectAttributes) {
        try {
            PersonDto personFound = service.findById(id);
            personFound.setMarried(married);
            service.save(personFound);
            String status = married ? "married" : "single";
            String message = "The Person id=" + id + " has been " + status;
            redirectAttributes.addFlashAttribute("message", message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/persons";
    }
}
