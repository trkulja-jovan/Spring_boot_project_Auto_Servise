package com.autoservis.projekat.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.autoservis.projekat.repository.PopravkaRepository;
import com.autoservis.projekat.repository.RadnikRepository;
import com.autoservis.projekat.repository.UslugaRepository;

import model.Popravka;
import model.Radnik;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/admin")
public class ReportController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	RadnikRepository rr;
	
	@Autowired
	PopravkaRepository pr;
	
	@Autowired
	UslugaRepository ur;
	
	@GetMapping("/getDataForIzvestaj")
	public String getData() {
		
		var radnici = rr.findAll()
				        .stream()
				        .filter(r -> r.getUloga().getNazivUloge().equals("WORKER"))
				        .collect(Collectors.toList());
		
		request.getSession().setAttribute("radnici", radnici);
		
		return "reports";
		
	}
	
	@GetMapping("/spisakPopravkiIzvestaj")
	public void popravkeZaRadnika(Integer radnik, Date datumOd, Date datumDo) {
		
		try {
			
			var r = rr.findById(radnik).get();
			
			if(datumOd.after(datumDo)) {
				greska();
				return;
			}
			
			var popravke = pr.getPopravkasBetweenDates(datumOd, datumDo, "Završena", r.getIdRadnik());
			
			if(popravke == null || popravke.size() == 0) {
				nemaPopravki();
				return;
			}

			var ukupnaCena = popravke.stream() 
					             	 .collect(Collectors.summingDouble(Popravka::getCena));
			
			generisiIzvestaj(r, popravke, ukupnaCena);
			
		} catch(Exception e) {
			e.printStackTrace();
			greska();
			return;
		}
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	private void generisiIzvestaj(Radnik r, List<Popravka> popravke, Double ukupnaCena) throws Exception {
		
		try (var out = response.getOutputStream(); 
			 var inputStream = this.getClass().getResourceAsStream("/reports/radnikIzvestaj.jrxml")) {
			
			response.setContentType("text/html");
			
			var dataSource = new JRBeanCollectionDataSource(popravke);
			
			var jasperReport = JasperCompileManager.compileReport(inputStream);
			
			var params = new HashMap<String, Object>();
			
			var radnik = r.getIme() + " " + r.getPrezime();
			var datumOd = popravke.get(0).getDatumPrijema().toString();
			var datumDo = popravke.get(0).getDatumZavrsetka().toString();
			
			params.put("radnik", radnik);
			params.put("datumOd", datumOd);
			params.put("datumDo", datumDo);
			params.put("profit", ukupnaCena);
			
			var jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			
			response.setContentType("application/x-download");
			response.addHeader("Content-disposition", "attachment; filename=PopravkeMajstora" + r.getIme() + ".pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}

	}
	
	private String greska() {
		request.getSession().setAttribute("greskaIzvestaj", true);
		return "greske";
	}
	
	private String nemaPopravki() {
		request.getSession().setAttribute("nemaPopravki", true);
		return "reports";
	}
	
	@GetMapping("/svePopravkeIzvestaj")
	public void svePopravke(Date datumOd, Date datumDo) {
		
		try {
			
			if(datumOd.after(datumDo)) {
				greska();
				return;
			}
			
			var popravke = pr.getPopravkasBetweenDates(datumOd, datumDo, "Završena");
			
			if(popravke == null || popravke.size() == 0) {
				nemaPopravki();
				return;
			}
			
			var ukupnaCena = popravke.stream() 
					             .collect(Collectors.summingDouble(Popravka::getCena));
			
			generisiIzvestajSvePopravke(popravke, ukupnaCena);
			
		} catch(Exception e) {
			e.printStackTrace();
			greska();
			return;
		}
	}
	
	@GetMapping("/sveUslugeIzvestaj")
	public void izvestajZaUsluge() {

		try (var out = response.getOutputStream(); 
			 var inputStream = this.getClass().getResourceAsStream("/reports/Usluge.jrxml")) {
			
			response.setContentType("text/html");
			
			var dataSource = new JRBeanCollectionDataSource(ur.findAll());
			
			var jasperReport = JasperCompileManager.compileReport(inputStream);
			
			var params = new HashMap<String, Object>();
			
			var jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			
			response.setContentType("application/x-download");
			response.addHeader("Content-disposition", "attachment; filename=Usluge.pdf");
			
			JasperExportManager.exportReportToPdfStream(jasperPrint,out);
			
		} catch(JRException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void generisiIzvestajSvePopravke(List<Popravka> popravke, Double ukupnaCena) {
		
		try (var out = response.getOutputStream();
			 var inputStream = this.getClass().getResourceAsStream("/reports/svePopravke.jrxml")) {
			
			response.setContentType("text/html");
			
			var dataSource = new JRBeanCollectionDataSource(popravke);
			
			var jasperReport = JasperCompileManager.compileReport(inputStream);
			
			var params = new HashMap<String, Object>();

			var datumOd = popravke.get(0).getDatumPrijema().toString();
			var datumDo = popravke.get(0).getDatumZavrsetka().toString();
			
			params.put("datumOd", datumOd);
			params.put("datumDo", datumDo);
			params.put("profit", ukupnaCena);
			
			var jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			
			response.setContentType("application/x-download");
			response.addHeader("Content-disposition", "attachment; filename=Sve_Popravke.pdf");
			
			JasperExportManager.exportReportToPdfStream(jasperPrint,out);
			
		} catch(JRException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
