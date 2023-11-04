package br.com.encurtador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.encurtador.dto.UrlDTO;
import br.com.encurtador.service.EncurtadorService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class EncurtaController {

	@Autowired
	private EncurtadorService urcurtaService;

	@RequestMapping(
			value = "/encurta",
			method = RequestMethod.POST,
			produces = { "application/json", "application/xml"}  
	)
	public ResponseEntity<UrlDTO> encurta(@RequestBody UrlDTO url) throws Exception {
		return ResponseEntity.ok().body(urcurtaService.encurta(url));
	}
	
	@RequestMapping(
			value = "/{urlEncurtada}",
			method = RequestMethod.GET,
			produces = { "application/json", "application/xml"}  
	)
	public void getUrl(HttpServletResponse response, @PathVariable String urlEncurtada) throws Exception {
		response.sendRedirect(urcurtaService.getUrl(urlEncurtada));
	}
	
	

}
