package com.autoservis.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.autoservis.projekat.repository.UslugaRepository;

import model.Usluga;

@Controller
public class UslugaController {
	
	@Autowired
	UslugaRepository ur;
	
	@Autowired
	HttpServletRequest request;
	
	@PostMapping("/admin/addUsluga")
	public String addUsluga(String nazUsluge, String cena) {
		
		var parsCena = 0.0;
		
		try {
			parsCena = Double.parseDouble(cena);
		} catch(NumberFormatException nfe) {
			request.getSession().setAttribute("greskaUsluga", true);
			return "greske";
		}
		
		var u = new Usluga();
		
		u.setCena(parsCena);
		u.setNazivUsluge(nazUsluge);
		
		ur.save(u);
		
		request.getSession().setAttribute("uspesnoUsluga", true);
		
		return "redirect:/admin/uslugePage";
		
	}
	
	@GetMapping("/admin/uslugePage")
	public String page() {
		return "usluge";
	}
	
	@GetMapping("/admin/getUsluge")
	public String getUsluge() {
		
		var usluge = ur.findAll();
		
		request.getSession().setAttribute("usluge", usluge);
		
		return "usluge";
	}

}
