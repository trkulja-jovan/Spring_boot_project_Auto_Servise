package com.autoservis.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.autoservis.projekat.repository.PopravkaRepository;

import model.Radnik;

@Controller
public class PopravkaController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	PopravkaRepository pr;
	
	@GetMapping("/admin/getPopravke")
	public String getPopravke() {
		
		var popravke = pr.findAll();
		
		request.getSession().setAttribute("svePopravke", popravke);
		
		return "popravke";
		
	}
	
	@GetMapping("/worker/getMojePopravke")
	public String getRadnikPopravke() {
		
		var r = (Radnik) request.getSession().getAttribute("radnik");
		
		var lista = pr.getPopravkeZaRadnika(r.getKorIme());
		
		request.getSession().setAttribute("mojePopravke", lista);
		
		return "popravke";
		
	}
	
	@GetMapping("/admin/detaljiPopravke")
	public String detalji(Integer id) {
		
		return "detalji";
	}
	

}
