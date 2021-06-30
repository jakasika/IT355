//package com.jaksa.se211.controller;
//
//import com.jaksa.se211.entity.KategorijeEntity;
//import com.jaksa.se211.entity.ProizvodiEntity;
//import com.jaksa.se211.form.ProizvodForm;
//import com.jaksa.se211.repository.KategorijaRepository;
//import com.jaksa.se211.repository.ProizvodRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "/admin/proizvodi")
//public class ProizvodiController {
//
//    @Autowired
//    ProizvodRepository proizvodRepository;
//
//    @Autowired
//    KategorijaRepository kategorijaRepository;
//
//    @GetMapping(value = "/")
//    public ModelAndView lista(){
//        ModelAndView mav = new ModelAndView("Admin/ProizvodList");
//        mav.addObject("proizvodi", proizvodRepository.findAll());
//
//        return mav;
//    }
//
//    @GetMapping(value = "/form")
//    public ModelAndView forma(){
//        ModelAndView mav = new ModelAndView("/Admin/ProizvodForm");
//        ProizvodForm proizvodForm = new ProizvodForm();
//        List<KategorijeEntity> kategorije = kategorijaRepository.findAll();
//        mav.addObject("proizvodForm", proizvodForm);
//        mav.addObject("kategorije", kategorije);
//
//        return mav;
//    }
//
//    @GetMapping(value = "/form")
//    public String snimi(@ModelAttribute ProizvodForm proizvodForm) {
//
//        String naziv = proizvodForm.getNaziv();
//        String opis = proizvodForm.getOpis();
//        BigDecimal cena = proizvodForm.getCena();
//        Integer kategorijaId = proizvodForm.getKategorijaId();
//        KategorijeEntity k = kategorijaRepository.getOne(kategorijaId);
//
//        ProizvodiEntity noviProizvod = new ProizvodiEntity(naziv,opis,cena, k);
//
//        if (proizvodForm.getId() > 0) {
//            noviProizvod = proizvodRepository.getOne(proizvodForm.getId());
//            noviProizvod.setNaziv(naziv);
//            noviProizvod.setOpis(opis);
//            noviProizvod.setCena(cena);
//            noviProizvod.setKategorija(k);
//
//
//        } else {
//            noviProizvod = new ProizvodiEntity(naziv, opis, cena, k);
//        }
//        proizvodRepository.save(noviProizvod);
//
//        return "redirect:/admin/proizvodi/";
//    }
//
//    @GetMapping(value = "/edit/{proizvodId}")
//    public ModelAndView edit(@PathVariable(value = "proizvodId")Integer proizvodId){
//        ModelAndView mav = new ModelAndView("/Admin/proizvodForm");
//        ProizvodiEntity proizvod = proizvodRepository.getOne(proizvodId);
//        List<KategorijeEntity> kategorije = kategorijaRepository.findAll();
//
//        ProizvodForm proizvodForm = new ProizvodForm();
//        proizvodForm.setId(proizvod.getId());
//        proizvodForm.setNaziv(proizvod.getNaziv());
//        proizvodForm.setOpis(proizvod.getOpis());
//        proizvodForm.setCena(proizvod.getCena());
//        proizvodForm.setKategorijaId(proizvod.getKategorija().getId());
//
//        mav.addObject("proizvodForm", proizvodForm);
//        mav.addObject("kategorije", kategorije);
//
//        return mav;
//    }
//
//    @GetMapping("/delete/{proizvodId}")
//    public String delete(@PathVariable(value = "proizvodId")Integer proizvodId,  Model model) {
//        ProizvodiEntity proizvod = proizvodRepository.findById(proizvodId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + proizvodId));
//        proizvodRepository.delete(proizvod);
//        model.addAttribute("proizvodi", proizvodRepository.findAll());
//        return "redirect:/admin/proizvodi/";
//    }
//}
