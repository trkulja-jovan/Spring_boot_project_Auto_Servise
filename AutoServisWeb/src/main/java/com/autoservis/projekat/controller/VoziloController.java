package com.autoservis.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.autoservis.projekat.repository.VoziloRepository;

@Controller
public class VoziloController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	VoziloRepository vr;
	
	@PostMapping("/worker/addVozilo")
	public String addVozilo() {
		
		return "";
		
	}

}
