package com.jaksa.it355.controller;


import com.jaksa.it355.entity.KategorijeEntity;
import com.jaksa.it355.entity.ProizvodiEntity;
import com.jaksa.it355.form.ProizvodForm;
import com.jaksa.it355.repository.KategorijaRepository;
import com.jaksa.it355.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/proizvodi")
public class ProizvodController {

    @Autowired
    ProizvodRepository proizvodRepository;

    @Autowired
    KategorijaRepository kategorijaRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView lista(){
        ModelAndView mav = new ModelAndView("Admin/ProizvodList");
        mav.addObject("proizvodi", proizvodRepository.findAll());

        return mav;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView forma(){
        ModelAndView mav = new ModelAndView("/Admin/ProizvodForm");
        ProizvodForm proizvodForm = new ProizvodForm();
        List<KategorijeEntity> kategorije = kategorijaRepository.findAll();
        mav.addObject("proizvodForm", proizvodForm);
        mav.addObject("kategorije", kategorije);

        return mav;
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String snimi(@ModelAttribute ProizvodForm proizvodForm) {

        String naziv = proizvodForm.getNaziv();
        String opis = proizvodForm.getOpis();
        BigDecimal cena = proizvodForm.getCena();
        Integer kategorijaId = proizvodForm.getKategorijaId();
        KategorijeEntity k = kategorijaRepository.getOne(kategorijaId);

        ProizvodiEntity noviProizvod = new ProizvodiEntity(naziv,opis,cena, k);

        if (proizvodForm.getId() > 0) {
            noviProizvod = proizvodRepository.getOne(proizvodForm.getId());
            noviProizvod.setNaziv(naziv);
            noviProizvod.setOpis(opis);
            noviProizvod.setCena(cena);
            noviProizvod.setKategorija(k);


        } else {
            noviProizvod = new ProizvodiEntity(naziv, opis, cena, k);
        }
        proizvodRepository.save(noviProizvod);

        return "redirect:/admin/proizvodi/";
    }

    @RequestMapping(value = "/edit/{proizvodId}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "proizvodId")Integer proizvodId){
        ModelAndView mav = new ModelAndView("/Admin/proizvodForm");
        ProizvodiEntity proizvod = proizvodRepository.getOne(proizvodId);
        List<KategorijeEntity> kategorije = kategorijaRepository.findAll();

        ProizvodForm proizvodForm = new ProizvodForm();
        proizvodForm.setId(proizvod.getId());
        proizvodForm.setNaziv(proizvod.getNaziv());
        proizvodForm.setOpis(proizvod.getOpis());
        proizvodForm.setCena(proizvod.getCena());
        proizvodForm.setKategorijaId(proizvod.getKategorija().getId());

        mav.addObject("proizvodForm", proizvodForm);
        mav.addObject("kategorije", kategorije);

        return mav;
    }

    @RequestMapping("/delete/{proizvodId}")
    public String delete(@PathVariable(value = "proizvodId")Integer proizvodId,  Model model) {
        ProizvodiEntity proizvod = proizvodRepository.findById(proizvodId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + proizvodId));
        proizvodRepository.delete(proizvod);
        model.addAttribute("proizvodi", proizvodRepository.findAll());
        return "redirect:/admin/proizvodi/";
    }
}
