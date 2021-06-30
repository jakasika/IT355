package com.jaksa.it355.controller;

import com.jaksa.it355.entity.KategorijeEntity;
import com.jaksa.it355.form.KategorijaForm;
import com.jaksa.it355.repository.KategorijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/kategorije")
public class KategorijaController {

    @Autowired
    KategorijaRepository kategorijaRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView lista(){
        ModelAndView mav = new ModelAndView("/Admin/KategorijaList");
        mav.addObject("kategorije", kategorijaRepository.findAll());

        return mav;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView forma(){
        ModelAndView mav = new ModelAndView("/Admin/KategorijaForm");
        KategorijaForm kategorijaForm = new KategorijaForm();
        List<KategorijeEntity> kategorijeList = kategorijaRepository.findAll();
        mav.addObject("kategorijaForm", kategorijaForm);
        mav.addObject("kategorije", kategorijeList);

        return mav;
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String snimi(@ModelAttribute KategorijaForm kategorijaForm) {

        String naziv = kategorijaForm.getNaziv();
        KategorijeEntity novaKategorija = new KategorijeEntity(naziv);

        if (kategorijaForm.getId() > 0) {
            novaKategorija = kategorijaRepository.getOne(kategorijaForm.getId());
            novaKategorija.setNaziv(naziv);
        } else {
            novaKategorija = new KategorijeEntity(naziv);
        }
        kategorijaRepository.save(novaKategorija);

        return "redirect:/admin/kategorije/";
    }

    @RequestMapping(value = "/edit/{kategorijaId}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "kategorijaId")Integer kategorijaId){
        ModelAndView mav = new ModelAndView("/Admin/KategorijaForm");
        KategorijeEntity kategorija = kategorijaRepository.getOne(kategorijaId);

        KategorijaForm kategorijaForm = new KategorijaForm();
        kategorijaForm.setId(kategorija.getId());
        kategorijaForm.setNaziv(kategorija.getNaziv());

        mav.addObject("kategorijaForm", kategorijaForm);

        return mav;
    }

    @RequestMapping("/delete/{kategorijaId}")
    public String delete(@PathVariable(value = "kategorijaId")Integer kategorijaId,  Model model) {
        KategorijeEntity kategorija = kategorijaRepository.findById(kategorijaId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + kategorijaId));
        kategorijaRepository.delete(kategorija);
        model.addAttribute("kategorije", kategorijaRepository.findAll());
        return "redirect:/admin/kategorije/";
    }
}
