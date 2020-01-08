package com.autoservis.projekat.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.autoservis.projekat.repository.PopravkaRepository;
import com.autoservis.projekat.repository.UslugaRepository;
import com.autoservis.projekat.repository.VoziloRepository;
import com.autoservis.projekat.session.Session;

import model.Popravka;
import model.Radnik;
import model.Status;
import model.Usluga;
import model.Vozilo;

@Controller
public class PopravkaController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	PopravkaRepository pr;

	@Autowired
	VoziloRepository vr;
	
	@Autowired
	UslugaRepository ur;

	@GetMapping("/admin/getPopravke")
	public String getPopravke() {

		var popravke = pr.findAll();

		request.getSession().setAttribute("svePopravke", popravke);

		return "popravke";

	}

	@GetMapping("/worker/getMojePopravke")
	public String getRadnikPopravke() {

		var r = (Radnik) request.getSession().getAttribute("radnik");

		var listaCeka = pr.getPopravkeZaRadnikaStatus("Čeka", r.getKorIme());
		var listaRadi = pr.getPopravkeZaRadnikaStatus("U procesu", r.getKorIme());
		var popravke = pr.getPopravkeZaRadnikaStatus("Završena", r.getKorIme());
		var mojePopravkePridruz = pr.getPopravkeZaRadnikaStatus("Pridruživanje", r.getKorIme());
		request.getSession().setAttribute("mojePopravkePridruz", mojePopravkePridruz);
		
		listaCeka.addAll(listaRadi);
		listaCeka.addAll(mojePopravkePridruz);
		
		request.getSession().setAttribute("mojeGotovePopravke", popravke);
		request.getSession().setAttribute("mojePopravke", listaCeka);

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

			if (datum.before(tren)) {
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

			var radnici = new ArrayList<Radnik>();
			radnici.add(Session.getRadnik());

			popravka.setRadniks(radnici);
			
			var vozila = new ArrayList<Vozilo>();
			popravka.setVozilos(vozila);

			pr.save(popravka);

			request.getSession().setAttribute("uspesnoPopravka", true);

			return "redirect:/worker/editPopravkePage";

		} catch (Exception e) {
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
			pr.save(popravka);
			request.getSession().setAttribute("uspesnoZapoceto", true);

		} catch (Exception e) {
			request.getSession().setAttribute("greskaPopravka", true);
			return "greske";
		}

		return "redirect:/worker/getPopravkaPage";
	}
	
	@GetMapping("/worker/getPopravkaPage")
	public String popravkaPage() {
		
		var listaRadi = pr.getPopravkeZaRadnikaStatus("U procesu", Session.getRadnik().getKorIme());
		request.getSession().setAttribute("mojePopravke", listaRadi);
		
		return "popravke";
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

		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("greskaPopravka", true);
			return "greske";
		}

		return "redirect:/admin/refreshData";
	}

	@GetMapping("/worker/getPopravke")
	public String getPopravkeWorker() {

		var popravke = pr.getPopravkeZaRadnikaStatus("U procesu", Session.getRadnik().getKorIme());
		var svePopravke = pr.getPopravkeZaPridruzivanje("U procesu", Session.getRadnik().getIdRadnik());

		request.getSession().setAttribute("popravke", popravke);
		request.getSession().setAttribute("popravkeZaPridr", svePopravke);

		return "redirect:/worker/editPopravkePage";
	}
	
	@PostMapping("/worker/updatePopravka")
	public String updatePopravka(Integer popravka, Date datum) {
		
		try {
			
			String[] selektovano = request.getParameterValues("usluge");	
			Integer[] parsirano = parsiraj(selektovano);
			
			if(parsirano == null) {
				request.getSession().setAttribute("greskaPopravka", true);
				return "greske";
			}
			
			var usluge = vratiUsluge(parsirano);
			var p = pr.findById(popravka).get();
			
			Date pocetak = p.getDatumPrijema();

			if (datum.before(pocetak)) {
				request.getSession().setAttribute("greskaPopravka", true);
				return "greske";
			}
			
			p.setDatumZavrsetka(datum);
			p.setUslugas(usluge);
			
			var status = new Status();
			status.setIdStatus(3);
			
			p.setStatus(status);
			
			var cena = usluge.stream()
					         .collect(Collectors.summingDouble(Usluga::getCena));
			
			p.setCena(cena);
			
			pr.save(p);
			
		} catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("greskaPopravka", true);
			return "greske";
		}
		
		return "redirect:/worker/getMojePopravke";
	}
	
	private Integer[] parsiraj(String[] selektovano) {
		
		Integer[] parsirano = new Integer[selektovano.length];
		var brojac = 0;
		
		for(var s: selektovano) {
			
			try {
				
				var broj = Integer.parseInt(s);
				parsirano[brojac++] = broj;
				
			} catch(NumberFormatException nfe) {
				return null;
			}
		}
		
		return parsirano;
		
	}
	
	private List<Usluga> vratiUsluge(Integer[] parsirano){
		
		List<Usluga> usluge = new ArrayList<>();
		
		for(var x: parsirano) {
			
			var u = ur.findById(x).get();
			usluge.add(u);
		}
		
		return usluge;
	}

}
