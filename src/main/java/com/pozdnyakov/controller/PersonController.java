package com.pozdnyakov.controller;

import com.pozdnyakov.model.Person;
import com.pozdnyakov.model.Role;
import com.pozdnyakov.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class PersonController{

    @Autowired(required=true)
    PersonService personService;

    private static final int PERSONS_ON_PAGE = 50;

    public void setPersonService(PersonService ps){
        this.personService = ps;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String read(/*@RequestParam(value = "page", required = false) Long page*/ Model model) {
//        if (page == null)page = 1L;
//        int personsCount = this.personService.getPersonsCount();
//        int startpage = (int) (page - 3 > 0?page - 3:1);
//        int pagesCount = (personsCount % PERSONS_ON_PAGE == 0) ?  personsCount / PERSONS_ON_PAGE : personsCount / PERSONS_ON_PAGE + 1;
//        int endpage = (startpage + 6) > pagesCount ? pagesCount : startpage + 6;
       // com.pozdnyakov.model.addAttribute("person", new Person());
        model.addAttribute("usersList", this.personService.read(1L, PERSONS_ON_PAGE));
//        com.pozdnyakov.model.addAttribute("startpage", startpage);
//        com.pozdnyakov.model.addAttribute("endpage", endpage);
        return "personsList";
    }




    //For add and update person both
    @RequestMapping(value= "/add", method = RequestMethod.POST)
    public String create(@ModelAttribute("person") Person p){
        if(p.getId() == null){
            //new person, add it
            this.personService.create(p);
        }else{
            //existing person, call update
            this.personService.update(p);
        }

        return "index";

    }

    @RequestMapping("/remove/{id}")
    public String delete(@PathVariable("id") int id){

        this.personService.delete(id);
        return "personsList";
    }

    @RequestMapping("/edit/{id}")
    public String update(@RequestParam(value = "page", required = false) Long page, @PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("usersList", this.personService.read(page == null? 1L : page, PERSONS_ON_PAGE));
        return "index";
    }

    @RequestMapping("/deleteAll")
    public String deleteAll(){
        this.personService.deleteAll();
        return "redirect:/";
    }

    @RequestMapping(value = "/search/", method = RequestMethod.GET)
    public String search(@RequestParam(value = "page", required = false) Long page,
                         @RequestParam(value = "name", required = false) String name,
                         @RequestParam String action,
                         Model model)
    {
        if (action.equals("Search person")) {
            if (page == null) page = 1L;
            int personsCount = this.personService.getPersonsCount(name);
            int startpage = (int) (page - 3 > 0?page - 3:1);
            int pagesCount = (personsCount % PERSONS_ON_PAGE == 0) ?  personsCount / PERSONS_ON_PAGE : personsCount / PERSONS_ON_PAGE + 1;
            int endpage = (startpage + 6) > pagesCount ? pagesCount : startpage + 6;
            model.addAttribute("person", new Person());
            model.addAttribute("searchName", name);
            model.addAttribute("usersList", this.personService.findByName(name, page, PERSONS_ON_PAGE));
            model.addAttribute("startpage", startpage);
            model.addAttribute("endpage", endpage);
            return "personsList";
        }
        else if (action.equals("Reset search")){
            read(model);
            return "personsList";
        }
        return "personsList";
    }

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/addperson")
    public String addPerson(Model model){
        model.addAttribute("person", new Person());
        List<Role> rolesList = Arrays.asList(Role.values());
        model.addAttribute("rolesList", rolesList);
        return "personAdd";
    }

}
