package com.jaksa.it355.controller;

import com.jaksa.it355.entity.KupacEntity;
import com.jaksa.it355.form.KupacForm;
import com.jaksa.it355.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/admin/kupci")
public class KupacController {

    @Autowired
    KupacRepository kupacRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView lista(){
        ModelAndView mav = new ModelAndView("/Admin/KupacLista");
        mav.addObject("kupci", kupacRepository.findAll());

        return mav;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView forma(){
        ModelAndView mav = new ModelAndView("/Admin/KupacForm");
        KupacForm kupacForm = new KupacForm();
        mav.addObject("kupacForm", kupacForm);

        return mav;
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String snimi(@ModelAttribute KupacForm kupacForm) {

        String ime = kupacForm.getIme();
        String prezime = kupacForm.getPrezime();
        String email = kupacForm.getEmail();
        KupacEntity noviKupac = new KupacEntity(ime, prezime, email);

        if (kupacForm.getId() > 0) {
            noviKupac = kupacRepository.getOne(kupacForm.getId());
            noviKupac.setIme(ime);
            noviKupac.setPrezime(prezime);
            noviKupac.setEmail(email);

        } else {
            noviKupac = new KupacEntity(ime, prezime, email);

        }

        kupacRepository.save(noviKupac);

        return "redirect:/admin/kupci/";
    }

    @RequestMapping(value = "/edit/{kupacId}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "kupacId")Integer kupacId){
        ModelAndView mav = new ModelAndView("/Admin/KupacForm");
        KupacEntity kupac = kupacRepository.getOne(kupacId);

        KupacForm kupacForm = new KupacForm();
        kupacForm.setId(kupac.getId());
        kupacForm.setIme(kupac.getIme());
        kupacForm.setPrezime(kupac.getPrezime());
        kupacForm.setEmail(kupac.getEmail());

        mav.addObject("kupacForm", kupacForm);

        return mav;
    }

    @RequestMapping("/delete/{kupacId}")
    public String delete(@PathVariable(value = "kupacId")Integer kupacId,  Model model) {
        KupacEntity kupac = kupacRepository.findById(kupacId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + kupacId));
        kupacRepository.delete(kupac);
        model.addAttribute("kupci", kupacRepository.findAll());
        return "redirect:/admin/kupci/";
    }
}
