package com.autoservis.projekat.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.autoservis.projekat.repository.RadnikRepository;

import model.Radnik;
import model.Uloga;

@Controller
@RequestMapping(value = "/")
public class RadnikController {    
	
	@Autowired
	private RadnikRepository rr;
	
	@RequestMapping(value = "/admin/getRadnici")
	public String getRadnikPodaci(HttpServletRequest request) {
		
		List<Radnik> radnici = rr.findAll()
				                 .stream()
				                 .filter(e -> !(e.getUloga().getNazivUloge().equals("ADMIN")))
				                 .collect(Collectors.toList());
		
		request.getSession().setAttribute("zaposleni", radnici);

		return "zaposleni";
	}
	
	@RequestMapping(value = "/admin/registerWorker", method=RequestMethod.POST)
	public String addUser(String ime, String prezime, String kvalif, String korIme, String password, 
						  HttpServletRequest request) {
		
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
			request.getSession().setAttribute("uspesno", false);
		}
		return "zaposleni";
	}
}
