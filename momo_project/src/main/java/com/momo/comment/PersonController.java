package com.momo.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PersonController {
	   @Autowired 

       private final PersonService personService;    

       public PersonController(PersonService personService) {

           this.personService = personService;

       }

  @RequestMapping(value = "/addPerson") // 2
   public String homePage(Model model) { 
       model.addAttribute("person", new Person()); // 3
       return "comment/makePerson"; // 4
   }

   @RequestMapping(value = "/person")
   public String getPagePerson(Model model) {
       model.addAttribute("persons", personService.getAllPersons()); // 5
       return "comment/result"; 
   }

   @RequestMapping(value = "/person", method = RequestMethod.POST) // 6
   public String addPagePerson(@ModelAttribute Person person, Model model) { // 7
       personService.createPerson(person); 
       model.addAttribute("persons", personService.getAllPersons());
       return "comment/result";
   }

   @RequestMapping(value = "/person/delete/{id}")
   public String deletePagePerson(@PathVariable Long id) { // 8
       personService.deletePerson(id); 
       return "redirect:/person"; // 9
   }
}
