package com.autoservis.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.autoservis.projekat.repository.UslugaRepository;

@Controller
public class UslugaController {
	
	@Autowired
	UslugaRepository ur;
	
	@Autowired
	HttpServletRequest request;
	
	@PostMapping("/admin/addUsluga")
	public String addUsluga(String nazUsluge, String cena) {
		
		Double parsirano = 0.0;
		
		try {
			parsirano = Double.parseDouble(cena);
		} catch(NumberFormatException nfe) {
			
		}
		
		return "";
		
	}

}
