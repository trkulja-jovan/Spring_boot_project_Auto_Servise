package com.autoservis.projekat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.autoservis.projekat.repository.PopravkaRepository;
import com.autoservis.projekat.repository.RadnikRepository;
import com.autoservis.projekat.session.Session;

import model.Popravka;
import model.Radnik;

@Controller
@RequestMapping(value = "/")
public class GeneralController {
	
	@Autowired
	RadnikRepository rr;
	
	@Autowired
	PopravkaRepository pr;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public String getAllValues(HttpServletRequest request) {
		
		Radnik r = Session.getRadnik();
     	String pass = Session.getPass();
     	
		request.getSession().setAttribute("radnik", r);
		request.getSession().setAttribute("radnikPass", pass);
		
		List<Radnik> radnici = rr.findAll();
		request.getSession().setAttribute("radnici", radnici);
		request.getSession().setAttribute("brRadnika", radnici.size());
		
		List<Popravka> popravkeCekanje = pr.getPopravkaByStatus("U procesu");
		request.getSession().setAttribute("popCekanje", popravkeCekanje);
		request.getSession().setAttribute("brCekanjePop", popravkeCekanje.size());
		
		List<Popravka> popravkePopravlja = pr.getPopravkaByStatus("Čeka");
		request.getSession().setAttribute("popProces", popravkePopravlja);
		request.getSession().setAttribute("brPopravlja", popravkePopravlja.size());
		
		return "index";
	}

}
