package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller

public class MainController {
    @Autowired
   // PersonRepository personRepo;
    PersonRepository personRepo;

    @RequestMapping("/")
    public String showIndex(Model model)
    {
        model.addAttribute("people",personRepo.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addPerson(Model model)
    {
        model.addAttribute("aPerson",new Person());
        return "addperson";
    }

    @PostMapping("/saveperson")
    public String savePerson(@Valid @ModelAttribute("person") Person person, BindingResult result)
    {

        if(result.hasErrors())
        {
            return "addperson";
        }
        personRepo.save(person);
        return "redirect:/";
    }
    @RequestMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("aPerson",personRepo.findById(id));
        return "addperson";
    }

}
