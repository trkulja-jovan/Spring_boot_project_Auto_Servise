package com.autoservis.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.autoservis.projekat.repository.KlijentRepository;
import com.autoservis.projekat.repository.VoziloRepository;

import model.Vozilo;

@Controller
public class VoziloController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	VoziloRepository vr;
	
	@Autowired
	KlijentRepository kr;
	
	@PostMapping("/worker/addVozilo")
	public String addVozilo(String marka, String regTab, Integer klijent) {
		
		try {
			
			var vozilo = vr.findByRegTablice(regTab);
			
			if(vozilo != null) {
				request.getSession().setAttribute("greskaVozilo", true);
				return "greske";
			}
			
			vozilo = null;
			
			var vlasnik = kr.findById(klijent).get();
			
			var v = new Vozilo();
			v.setMarka(marka);
			v.setRegTablice(regTab);
			v.setVlasnik(vlasnik);
			
			vr.saveAndFlush(v);
			
			request.getSession().setAttribute("uspesnoVozilo", true);
			
			return "redirect:/getKlijenti";
			
		} catch(Exception e) {
			request.getSession().setAttribute("greskaVozilo", true);
			return "greske";
		}
		
	}
}
