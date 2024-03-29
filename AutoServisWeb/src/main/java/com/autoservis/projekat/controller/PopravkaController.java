package com.autoservis.projekat.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.autoservis.projekat.repository.PopravkaRepository;
import com.autoservis.projekat.repository.RadnikRepository;
import com.autoservis.projekat.repository.UslugaRepository;
import com.autoservis.projekat.repository.VoziloRepository;
import com.autoservis.projekat.session.Data;
import com.autoservis.projekat.session.Session;

import model.Popravka;
import model.Radnik;
import model.Status;
import model.Usluga;
import model.Vozilo;

@Controller
public class PopravkaController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private PopravkaRepository pr;

	@Autowired
	private VoziloRepository vr;

	@Autowired
	private UslugaRepository ur;

	@Autowired
	private RadnikRepository rr;

	@ExceptionHandler(Exception.class)
	public String returnErr() {
		request.getSession().setAttribute("greskaPopravka", true);
		return "greske";
	}

	@GetMapping("/admin/getPopravke")
	public String getPopravke() {

		var popravke = pr.findAll();
		var radnici = rr.findByRole("WORKER");

		var dataRadnik = radnici.stream()
								.map(r -> {

									var brZavr = pr.getBrojPopravkiZaRadnikaByStatus(r.getIdRadnik(), "Završena");
									var brProc = pr.getBrojPopravkiZaRadnikaByStatus(r.getIdRadnik(), "U procesu");
									var ceka = pr.getBrojPopravkiZaRadnikaByStatus(r.getIdRadnik(), "Čeka");
									
									var ime = r.getIme();
									var prezime = r.getPrezime();

									return new Data(ime, prezime, brZavr, brProc, ceka);

								})
								.sorted(Comparator.comparing(Data::getPrezime))
								.collect(Collectors.toList());

		request.getSession().setAttribute("svePopravke", popravke);
		request.getSession().setAttribute("data", dataRadnik);

		return "popravke";

	}

	@GetMapping("/worker/getMojePopravke")
	public String getRadnikPopravke() {

		var r = (Radnik) request.getSession().getAttribute("radnik");

		var listaCeka = pr.getPopravkeZaRadnikaStatus("Čeka", r.getKorIme());
		var listaRadi = pr.getPopravkeZaRadnikaStatus("U procesu", r.getKorIme());
		var popravke = pr.getPopravkeZaRadnikaStatus("Završena", r.getKorIme());

		listaCeka.addAll(listaRadi);

		request.getSession().setAttribute("mojeGotovePopravke", popravke);
		request.getSession().setAttribute("mojePopravke", listaCeka);

		return "popravke";

	}

	@GetMapping("/admin/detaljiPopravke")
	public String detalji(Integer id) {

		var popravka = pr.findById(id).get();

		request.getSession().setAttribute("popravka", popravka);
		request.getSession().setAttribute("id", id);

		return "redirect:/admin/detaljiKlijentForPopravka";
	}

	@PostMapping("/worker/addPopravka")
	public String addPopravka(String opis, Date datum, Integer vozilo) {

		var trenDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

		if (datum.before(trenDate)) {
			request.getSession().setAttribute("greskaPopravka", true);
			return "greske";
		}

		var popravka = new Popravka();
		popravka.setDatumPrijema(datum);
		popravka.setOpisPopravke(opis);

		var status = new Status();
		status.setIdStatus(1);

		popravka.setStatus(status);

		var radnici = new ArrayList<Radnik>();
		radnici.add(Session.getRadnik());

		popravka.setRadniks(radnici);

		var vozila = new ArrayList<Vozilo>();
		vozila.add(vr.findById(vozilo).get());

		popravka.setVozilos(vozila);

		pr.save(popravka);

		request.getSession().setAttribute("uspesnoPopravka", true);

		return "redirect:/worker/editPopravkePage";

	}

	@GetMapping("/worker/editPopravkePage")
	public String getPopravkePage() {
		return "editPopravke";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		var sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@PostMapping("/worker/changePopravkaData")
	public String changePopravka(Integer idPopravka) {

		var popravka = pr.findById(idPopravka).get();

		var status = new Status();

		status.setIdStatus(2);
		popravka.setStatus(status);

		pr.save(popravka);

		request.getSession().setAttribute("uspesnoZapoceto", true);

		return "redirect:/worker/getMojePopravke";
	}

	@GetMapping("/worker/getPopravkaPage")
	public String popravkaPage() {

		var listaRadi = pr.getPopravkeZaRadnikaStatus("U procesu", Session.getRadnik().getKorIme());
		request.getSession().setAttribute("mojePopravke", listaRadi);

		return "popravke";
	}

	@PostMapping("/admin/changePopravkaData")
	public String changePopravkaAdmin(Integer idPopravka) {

		var popravka = pr.findById(idPopravka).get();

		var status = new Status();
		status.setIdStatus(4);

		popravka.setStatus(status);

		pr.save(popravka);

		request.getSession().setAttribute("uspesnoOdobreno", true);

		return "redirect:/admin/refreshData";
	}

	@GetMapping("/worker/getPopravke")
	public String getPopravkeWorker() {

		var popravke = pr.getPopravkeZaRadnikaStatus("U procesu", Session.getRadnik().getKorIme());
		var svePopravke = pr.getPopravkaByStatus("U procesu");

		request.getSession().setAttribute("popravke", popravke);
		request.getSession().setAttribute("popravkeZaPridr", svePopravke);

		return "redirect:/worker/editPopravkePage";
	}

	@PostMapping("/worker/updatePopravka")
	public String updatePopravka(Integer popravka, Date datum) {

		var selektovano = request.getParameterValues("usluge");

		if (selektovano == null || selektovano.length == 0) {
			request.getSession().setAttribute("greskaPopravka", true);
			return "greske";
		}

		var usluge = vratiUsluge(selektovano);

		var p = pr.findById(popravka).get();

		var pocetak = p.getDatumPrijema();

		if (datum.before(pocetak)) {
			request.getSession().setAttribute("greskaPopravka", true);
			return "greske";
		}

		p.setDatumZavrsetka(datum);
		p.setUslugas(usluge);

		var status = new Status();
		status.setIdStatus(3);
		p.setStatus(status);

		var cena = usluge.stream().collect(Collectors.summingDouble(Usluga::getCena));

		p.setCena(cena);

		pr.save(p);

		return "redirect:/worker/getMojePopravke";
	}

	private List<Usluga> vratiUsluge(String[] selektovano) {

		return Arrays.stream(selektovano)
					 .map(Integer::parseInt)
					 .map(u -> ur.findById(u).get())
					 .collect(Collectors.toList());
	}	

}
