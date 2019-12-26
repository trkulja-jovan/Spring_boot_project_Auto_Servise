package com.autoservis.projekat.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.autoservis.projekat.repository.RadnikRepository;

import model.Radnik;

@Controller
@RequestMapping(value = "/")
public class RadnikController {    
	
	@Autowired
	private RadnikRepository rr;
	
	@RequestMapping(value = "/getRadnik")
	public String getRadnikPodaci(HttpServletRequest request) {
		
		
		return "myProfile";
	}
	
	@RequestMapping(value = "/admin/changeData", method = RequestMethod.POST)
	public String changeData(String kor_ime_novo, String pass_novo, String pass_novo1, HttpServletRequest request) {

		if(Objects.deepEquals(pass_novo, pass_novo1)) {
			
			Radnik radnik = (Radnik) request.getSession().getAttribute("radnik");
			
			radnik.setKorIme(kor_ime_novo);
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	     	radnik.setPassword(passwordEncoder.encode(pass_novo));
			
			rr.save(radnik);
			
			request.getSession().setAttribute("uspesno", true);
			
		} else {
			request.getSession().setAttribute("podacilosi", true);
		}
		
		return "myProfile";
	}
}
