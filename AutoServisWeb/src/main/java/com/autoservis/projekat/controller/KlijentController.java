package com.autoservis.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.autoservis.projekat.repository.KlijentRepository;
import com.autoservis.projekat.repository.VoziloRepository;

import model.Vlasnik;

@Controller
public class KlijentController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private KlijentRepository kr;
	
	@Autowired
	private VoziloRepository vr;
	
	@GetMapping("/worker/klijentiPage")
	public String klijentiPage() {
		return "klijenti";
	}

	@GetMapping("/admin/detaljiKlijenta")
	public String vozilaK(Integer idV) {
		
		var klijent = kr.findById(idV).get();
		request.getSession().setAttribute("k", klijent);
		
		var vozila = vr.findByVlasnik(idV);
		request.getSession().setAttribute("vozila", vozila);
		
		return "klijenti";
		
	}
	
	@PostMapping("/worker/addKlijent")
	public String addKlijent(String ime, String prez, String mesto) {
		
		try {
			
			var vlasnik = new Vlasnik();
			
			vlasnik.setIme(ime);
			vlasnik.setPrezime(prez);
			vlasnik.setMesto(mesto);
			
			kr.save(vlasnik);
			
			request.getSession().setAttribute("uspesnoKlijent", true);
			
			return "redirect:/getKlijenti";
			
		} catch(Exception e) {
			
			request.getSession().setAttribute("greskaKlijent", true);
			return "greske";
		}
	}
	
	@GetMapping("/admin/detaljiKlijentForPopravka")
	public String detaljiForPopravka() {
		
		var id = (Integer) request.getSession().getAttribute("id");
		var vozila = vr.getVozilas(id);
		
		if(vozila != null)
			request.getSession().setAttribute("vozila", vozila);
		
		return "redirect:/admin/getRadniks";
	}

}
