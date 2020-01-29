package com.autoservis.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.autoservis.projekat.repository.UslugaRepository;

import model.Usluga;

@Controller
public class UslugaController {
	
	@Autowired
	private UslugaRepository ur;
	
	@Autowired
	private HttpServletRequest request;
	
	@ExceptionHandler(NumberFormatException.class)
	public String returnErr() {
		request.getSession().setAttribute("greskaUsluga", true);
		return "greske";
	}
	
	@PostMapping("/admin/addUsluga")
	public String addUsluga(String nazUsluge, String cena) {
		
		var usluga = ur.findByNazivUsluge(nazUsluge);
		
		if(usluga != null) {
			request.getSession().setAttribute("greskaUsluga", true);
			return "greske";
		}

		var parsCena = Double.parseDouble(cena);

		var u = new Usluga();
		
		u.setCena(parsCena);
		u.setNazivUsluge(nazUsluge);
		
		ur.saveAndFlush(u);
		
		request.getSession().setAttribute("uspesnoUsluga", true);
		
		return "redirect:/admin/getUsluge";
		
	}
	
	@GetMapping("/admin/getUsluge")
	public String getUsluge() {
		
		var usluge = ur.findAll();
		
		request.getSession().setAttribute("usluge", usluge);
		
		return "usluge";
	}
	
	@GetMapping("/admin/getUslugeForDetalji")
	public String getUslugas() {
		
		var id = (Integer) request.getSession().getAttribute("id");
		
		var usluge = ur.getUsluge(id);
		
		if(usluge != null)
			request.getSession().setAttribute("usluge", usluge);
		
		request.getSession().removeAttribute("id");
		
		return "detalji";
	}

}
