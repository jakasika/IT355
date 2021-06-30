package com.jaksa.it355.controller;


import com.jaksa.it355.entity.*;
import com.jaksa.it355.form.KupacForm;
import com.jaksa.it355.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/proizvodi")
public class UserController {

    @Autowired
    ProizvodRepository proizvodRepository;

    @Autowired
    KategorijaRepository kategorijaRepository;

    @Autowired
    KupacRepository kupacRepository;

    @Autowired
    NarudzbenicaRepository narudzbenicaRepository;

    @Autowired
    NaruceniProizvodiRepository naruceniProizvodiRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView lista(){
        ModelAndView mav = new ModelAndView("/User/ProizvodiUser");
        mav.addObject("proizvodi", proizvodRepository.findAll());
        mav.addObject("kategorije", kategorijaRepository.findAll());

        return mav;
    }

    @RequestMapping(value = "/potvrda")
    public ModelAndView potvrda(Model model, HttpServletRequest request, @RequestParam("kolicina") String[] kolicina) {
        ModelAndView mav = new ModelAndView("/User/Potvrda");
        KupacForm kupacForm = new KupacForm();

        ArrayList<NaruceniProizvodiEntity> izabraniProizvodi = new ArrayList<>();
//        kupacForm.setProizvodi(izabraniProizvodi);

        Timestamp vreme = Timestamp.valueOf(LocalDateTime.now());

        BigDecimal ukupnacena = new BigDecimal("0.00");
        NarudzbeniceEntity narudzbenica = new NarudzbeniceEntity(vreme);
        narudzbenicaRepository.save(narudzbenica);

        for(int i = 0; i < kolicina.length; i++) {
            System.out.println(kolicina[i]);
            String[] parts = kolicina[i].split(":");
            String part1 = parts[0]; // 004
            String part2 = parts[1]; // 034556
            Integer proizvodId = (Integer.parseInt(part1));
            Integer kol = (Integer.parseInt(part2));
            if(kol > 0){
                ProizvodiEntity proizvod = proizvodRepository.getOne(proizvodId);

                NaruceniProizvodiEntity naruceniProizvod = new NaruceniProizvodiEntity(proizvod.getCena(), kol, narudzbenica, proizvod);
                naruceniProizvodiRepository.save(naruceniProizvod);
                naruceniProizvodiRepository.flush();
                izabraniProizvodi.add(naruceniProizvod);
                ukupnacena = ukupnacena.add(proizvod.getCena().multiply(BigDecimal.valueOf(kol)));

            }
        }
        narudzbenica.setUkupnaCena(ukupnacena);
        narudzbenicaRepository.saveAndFlush(narudzbenica);
        mav.addObject("izabraniproizvodi", izabraniProizvodi);
        mav.addObject("narudzbenica", narudzbenica);

        mav.addObject("kupacForm", kupacForm);

        return mav;
    }


    @RequestMapping(value = "/kupacform", method = RequestMethod.POST)
    public String snimi(@ModelAttribute KupacForm kupacForm) {

        String ime = kupacForm.getIme();
        String prezime = kupacForm.getPrezime();
        String email = kupacForm.getEmail();
        KupacEntity noviKupac = new KupacEntity(ime, prezime, email);

        kupacRepository.save(noviKupac);

        System.out.println("narudzbenica: " + kupacForm.getNarudzbenica().getId());
        NarudzbeniceEntity narudzbeniceEntity = narudzbenicaRepository.getOne(kupacForm.getNarudzbenica().getId());
        narudzbeniceEntity.setKupac(noviKupac);
        narudzbenicaRepository.saveAndFlush(narudzbeniceEntity);

        return "/User/Success";
    }
}
