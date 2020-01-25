package com.autoservis.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.autoservis.projekat.repository.RadnikRepository;

import model.Radnik;
import model.Uloga;

@Controller
@RequestMapping("/")
public class RadnikController {    
	
	@Autowired
	private RadnikRepository rr;
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping("/admin/radnikPage")
	public String page() {
		return "zaposleni";
	}
	
	@GetMapping("/admin/getRadnici")
	public String getRadnikPodaci() {
		
		var radnici = rr.findByRole("WORKER");
		
		request.getSession().setAttribute("zaposleni", radnici);

		return "zaposleni";
	}

	@PostMapping("/admin/registerWorker")
	public String dodajRadnika(String ime, String prezime, String kvalif, String korIme, String password) {
		
		try {
			
			var postojiR = rr.findByKorIme(korIme);
			
			if(postojiR != null) {
				request.getSession().setAttribute("greskaRadnik", true);
				return "greske";
			}
			
			var r = new Radnik();
			
			r.setIme(ime);
			r.setPrezime(prezime);
			r.setKvalifikacije(kvalif);
			r.setKorIme(korIme);
			
			var passwordEncoder = new BCryptPasswordEncoder();
			r.setPassword(passwordEncoder.encode(password));
			
			var u = new Uloga();
			u.setIdUloga(2);
			r.setUloga(u);
			
			rr.save(r);
			
			request.getSession().setAttribute("uspesno", true);

		} catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("greskaRadnik", true);
			return "greske";
		}
		
		return "redirect:/admin/radnikPage";
	}
	
	@GetMapping("/admin/getRadniks")
	public String getRadniks() {
		
		var id = (Integer) request.getSession().getAttribute("id");
		
		var radniciNaPopravci = rr.getRadniks(id);
		
		if(radniciNaPopravci != null)
			request.getSession().setAttribute("radnici", radniciNaPopravci);
		
		return "redirect:/admin/getUslugeForDetalji";
		
	}

}
