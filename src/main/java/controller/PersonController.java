package controller;

import dao.PersonService;
import mysql.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class PersonController{
    @Autowired(required=true)
    PersonService personService;

    private static final int PERSONS_ON_PAGE = 3;

    public void setPersonService(PersonService ps){
        this.personService = ps;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String read(@RequestParam(value = "page", required = false) Long page, Model model) {
        if (page == null)page = 1L;
        int personsCount = this.personService.getPersonsCount();
        int startpage = (int) (page - 3 > 0?page - 3:1);
        int pagesCount = (personsCount % PERSONS_ON_PAGE == 0) ?  personsCount / PERSONS_ON_PAGE : personsCount / PERSONS_ON_PAGE + 1;
        int endpage = (startpage + 6) > pagesCount ? pagesCount : startpage + 6;
        model.addAttribute("person", new User());
        model.addAttribute("listPersons", this.personService.read(page, PERSONS_ON_PAGE));
        model.addAttribute("startpage", startpage);
        model.addAttribute("endpage", endpage);
        return "index";
    }


    //For add and update person both
    @RequestMapping(value= "/person/add", method = RequestMethod.POST)
    public String create(@ModelAttribute("person") User p){
        if(p.getId() == 0){
            //new person, add it
            this.personService.create(p);
        }else{
            //existing person, call update
            this.personService.update(p);
        }

        return "redirect:/";

    }

    @RequestMapping("/remove/{id}")
    public String delete(@PathVariable("id") int id){

        this.personService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String update(@RequestParam(value = "page", required = false) Long page, @PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.read(page == null? 1L : page, PERSONS_ON_PAGE));
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
            model.addAttribute("person", new User());
            model.addAttribute("searchName", name);
            model.addAttribute("listPersons", this.personService.findByName(name, page, PERSONS_ON_PAGE));
            model.addAttribute("startpage", startpage);
            model.addAttribute("endpage", endpage);
            return "index";
        }
        else if (action.equals("Reset search")){
            read(1L, model);
            return "redirect:/";
        }
        return "/";
    }

}
