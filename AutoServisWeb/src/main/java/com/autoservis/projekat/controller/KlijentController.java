package com.autoservis.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.autoservis.projekat.repository.KlijentRepository;
import com.autoservis.projekat.repository.VoziloRepository;

@Controller
public class KlijentController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	KlijentRepository kr;
	
	@Autowired
	VoziloRepository vr;
	
	@GetMapping("/admin/getKlijenti")
	public String klijenti() {
		
		var klijenti = kr.findAll();
		
		request.getSession().setAttribute("klijenti", klijenti);
		
		return "klijenti";
	}
	
	@GetMapping("/admin/detaljiKlijenta")
	public String vozilaK(Integer idV) {
		
		var vozilaK = vr.findByVlasnik(idV);
		
		request.getSession().setAttribute("vozila", vozilaK);
		
		return "klijenti";
	}

}
