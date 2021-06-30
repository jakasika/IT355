package com.jaksa.it355.controller;


import com.jaksa.it355.repository.NarudzbenicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/narudzbenice")
public class NarudzbeniceController {

    @Autowired
    NarudzbenicaRepository narudzbenicaRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView lista(){
        ModelAndView mav = new ModelAndView("/Admin/NarudzbeniceList");
        mav.addObject("narudzbenice", narudzbenicaRepository.findAll());

        return mav;
    }
}
