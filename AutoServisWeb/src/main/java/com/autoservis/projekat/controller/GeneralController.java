package com.autoservis.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.autoservis.projekat.repository.KlijentRepository;
import com.autoservis.projekat.repository.PopravkaRepository;
import com.autoservis.projekat.repository.RadnikRepository;
import com.autoservis.projekat.session.Session;

@Controller
@RequestMapping("/")
public class GeneralController {
	
	@Autowired
	RadnikRepository rr;
	
	@Autowired
	PopravkaRepository pr;
	
	@Autowired
	KlijentRepository kr;
	
	@Autowired
	HttpServletRequest request;
	
	@GetMapping("/getAll")
	public String getAllValues() {
		
		var r = Session.getRadnik();
		var pass = Session.getPass();
     	
		var l = pr.getPopravkaBroj("Čeka");
		var l2 = pr.getPopravkaBroj("U procesu");
		
		var rad = rr.count();
		
		request.getSession().setAttribute("brCekanjePop", l);
		request.getSession().setAttribute("brPopravlja", l2);
		request.getSession().setAttribute("brRadnika", rad);
		request.getSession().setAttribute("radnik", r);
		request.getSession().setAttribute("radnikPass", pass);
		
		return "index";
	}
	
	@GetMapping("/admin/refreshData")
	public String getRefreshData() {
		
		var l = pr.getPopravkaBroj("Čeka");
		var l2 = pr.getPopravkaBroj("U procesu");
		
		var rad = rr.count();
		
		request.getSession().setAttribute("brCekanjePop", l);
		request.getSession().setAttribute("brPopravlja", l2);
		request.getSession().setAttribute("brRadnika", rad);
		
		return "index";
	}
	
	@GetMapping("/worker/refreshData") public String getRefreshDataWorker() {
		
		/*TODO: implementirati refresh data za radnika
		 * radniku na pocetnoj stranici prikazati status popravke na kojoj on radi
		 * smisliti sta jos
		 */
		
		return "";
	}
	
	@GetMapping("/getKlijenti")
	public String getKlijenti() {
		
		var klijenti = kr.findAll();
		
		request.getSession().setAttribute("klijenti", klijenti);
		
		return "klijenti";
	}

}
