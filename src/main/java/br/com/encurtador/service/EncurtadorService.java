package br.com.encurtador.service;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.encurtador.dto.UrlDTO;
import br.com.encurtador.entity.UrlEncurtada;
import br.com.encurtador.exception.GenericException;
import br.com.encurtador.exception.NotFoundException;
import br.com.encurtador.utils.Validador;

@Service
public class EncurtadorService {
	
	@Autowired
	private UrlRepository urlRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public UrlDTO encurta(UrlDTO url) throws Exception {
		if(Validador.valideStringVazia(url.getUrl())) 
			throw new GenericException("URL está vazia");	
		return save(url);
	}
	
	public UrlDTO save(UrlDTO dto) {
		UrlEncurtada url = modelMapper.map(dto, UrlEncurtada.class);
		url.setUrlEncurtada(geraLinkEncurtado());		
		UrlEncurtada save = urlRepository.save(url);
		return modelMapper.map(save, UrlDTO.class);
	}

	public String getUrl(String urlEncurtada) throws Exception {
		 Optional<UrlEncurtada> url = urlRepository.findByUrlEncurtada(urlEncurtada);
		 if(url.isPresent())
			 return url.get().getUrl();	 
		 throw new NotFoundException();
	}
	
	private String geraLinkEncurtado() {
		return RandomStringUtils.randomAlphabetic(8);
	}

}
