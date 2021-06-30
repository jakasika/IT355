package com.jaksa.it355.controller;

import com.jaksa.it355.repository.NaruceniProizvodiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/naruceniproizvodi")
public class NaruceniProizvodiController {

    @Autowired
    NaruceniProizvodiRepository naruceniProizvodiRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView lista(){
        ModelAndView mav = new ModelAndView("/Admin/NaruceniProizvodiList");
        mav.addObject("naruceniProizvodi", naruceniProizvodiRepository.findAll());

        return mav;
    }
}
