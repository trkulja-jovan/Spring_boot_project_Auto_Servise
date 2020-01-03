package com.autoservis.projekat.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.autoservis.projekat.repository.PopravkaRepository;
import com.autoservis.projekat.repository.VoziloRepository;
import com.autoservis.projekat.session.Session;

import model.Popravka;
import model.Radnik;
import model.Status;

@Controller
public class PopravkaController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	PopravkaRepository pr;
	
	@Autowired
	VoziloRepository vr;
	
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
	
	@PostMapping("/worker/addPopravka")
	public String addPopravka(String opis, Date datum, Integer vozilo) {
		
		try {
			
			Date tren = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			if(datum.before(tren)) {
				request.getSession().setAttribute("greskaPopravka", true);
				return "greske";
			}
			
			var popravka = new Popravka();
			popravka.setDatumPrijema(datum);
			popravka.setDatumZavrsetka(datum);
			popravka.setOpisPopravke(opis);
			
			var status = new Status();
			status.setIdStatus(1);
			
			popravka.setStatus(status);
			
			var radnik = new ArrayList<Radnik>();
			radnik.add(Session.getRadnik());
			
			popravka.setRadniks(radnik);
			
			pr.save(popravka);
			
			request.getSession().setAttribute("uspesnoPopravka", true);
			
			return "redirect:/worker/editPopravkePage";
			
		} catch(Exception e) {
			request.getSession().setAttribute("greskaPopravka", true);
			return "greske";
		}
	}
	
	@GetMapping("/worker/editPopravkePage")
	public String getPopravkePage() {
		return "editPopravke";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@PostMapping("/worker/changePopravkaData")
	public String changePopravka(Integer idPopravka) {
		
		try {
			
			var popravka = pr.findById(idPopravka).get();
			var status = new Status();
			status.setIdStatus(2);
			
			popravka.setStatus(status);
			
			
			popravka.setStatus(status);
			
			pr.save(popravka);
			
			request.getSession().setAttribute("uspesnoZapoceto", true);
			
		} catch(Exception e) {
			request.getSession().setAttribute("greskaPopravka", true);
			return "greske";
		}
		
		return "redirect:/worker/getMojePopravke";
	}
	
	@PostMapping("/admin/changePopravkaData")
	public String changePopravkaAdmin(Integer idPopravka) {
		
		try {
			
			var popravka = pr.findById(idPopravka).get();
			var status = new Status();
			status.setIdStatus(4);
			
			popravka.setStatus(status);
			
			pr.save(popravka);
			
			request.getSession().setAttribute("uspesnoOdobreno", true);
			
		} catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("greskaPopravka", true);
			return "greske";
		}
		
		return "redirect:/admin/refreshData";
	}
	

}
