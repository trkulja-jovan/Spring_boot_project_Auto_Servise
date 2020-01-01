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
	RadnikRepository rr;
	
	@Autowired
	HttpServletRequest request;
	
	@GetMapping("/admin/getRadnici")
	public String getRadnikPodaci() {
		
		var radnici = rr.findByRole("WORKER");
		
		request.getSession().setAttribute("zaposleni", radnici);

		return "zaposleni";
	}

	@PostMapping("/admin/registerWorker")
	public String dodajRadnika(String ime, String prezime, String kvalif, String korIme, String password) {
		
		try {
			
			Radnik r = new Radnik();
			
			r.setIme(ime);
			r.setPrezime(prezime);
			r.setKvalifikacije(kvalif);
			r.setKorIme(korIme);
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			r.setPassword(passwordEncoder.encode(password));
			
			Uloga u = new Uloga();
			u.setIdUloga(2);
			
			r.setUloga(u);
			
			rr.save(r);
			
			request.getSession().setAttribute("uspesno", true);

		} catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("greskaRadnik", true);
			return "greske";
		}
		
		return "zaposleni";
	}
}
