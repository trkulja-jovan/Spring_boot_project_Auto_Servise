package com.autoservis.projekat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.autoservis.projekat.repository.PopravkaRepository;

import model.Popravka;
import model.Radnik;

@Controller
public class PopravkaController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	PopravkaRepository pr;
	
	@GetMapping("/admin/getPopravke")
	public String getPopravke() {
		
		List<Popravka> popravke = pr.findAll();
		
		request.getSession().setAttribute("svePopravke", popravke);
		
		return "popravke";
		
	}
	
	@GetMapping("/worker/getMojePopravke")
	public String getRadnikPopravke() {
		
		Radnik r = (Radnik) request.getSession().getAttribute("radnik");
		
		List<Popravka> lista = pr.getPopravkeZaRadnika(r.getKorIme());
		
		request.getSession().setAttribute("mojePopravke", lista);
		
		return "popravke";
		
	}
	
	@GetMapping("/admin/detaljiPopravke")
	public String detalji(Integer id) {
		
		return "detalji";
	}
	
	@GetMapping("/worker/detaljiPopravke")
	public String detaljiRadnik(Integer id) {
		
		return "detalji";
	}
	

}