package br.com.encurtador.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.encurtador.dto.UrlDTO;
import br.com.encurtador.entity.UrlEncurtada;
import br.com.encurtador.exception.GenericException;
import br.com.encurtador.exception.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EncurtadorServiceTest {

    @Autowired
    private EncurtadorService encurtadorService;

    @MockBean
    private UrlRepository urlRepository;

    @Mock
    private ModelMapper modelMapper;

    @Before
    public void setup() {
        // Configurar comportamento de objetos simulados
        UrlDTO urlDto = new UrlDTO();
        urlDto.setUrl("https://example.com");

        UrlEncurtada urlEncurtada = new UrlEncurtada();
        urlEncurtada.setUrl("https://example.com");
        urlEncurtada.setUrlEncurtada("abc123");

        Mockito.when(modelMapper.map(urlDto, UrlEncurtada.class)).thenReturn(urlEncurtada);
        Mockito.when(urlRepository.save(Mockito.any(UrlEncurtada.class))).thenReturn(urlEncurtada);
    }

    @Test
    public void testEncurtaValidUrl() throws Exception {
        UrlDTO urlDto = new UrlDTO();
        urlDto.setUrl("https://example.com");

        UrlDTO result = encurtadorService.encurta(urlDto);

        assertEquals("abc123", result.getUrlEncurtada());
    }

    @Test
    public void testEncurtaEmptyUrl() throws Exception {
        UrlDTO urlDto = new UrlDTO();
        urlDto.setUrl("");

        try {
            encurtadorService.encurta(urlDto);
            fail("Expected GenericException, but no exception was thrown.");
        } catch (GenericException e) {
            // O teste passa se uma GenericException for lançada
        }
    }

    @Test
    public void testGetUrlByEncurtada() throws Exception {
        String encurtada = "abc123";

        UrlEncurtada urlEncurtada = new UrlEncurtada();
        urlEncurtada.setUrl("https://example.com");
        urlEncurtada.setUrlEncurtada(encurtada);

        Mockito.when(urlRepository.findByUrlEncurtada(encurtada)).thenReturn(Optional.of(urlEncurtada));

        String result = encurtadorService.getUrl(encurtada);

        assertEquals("https://example.com", result);
    }

    @Test
    public void testGetUrlByEncurtadaNotFound() throws Exception {
        String encurtada = "nonexistent";

        Mockito.when(urlRepository.findByUrlEncurtada(encurtada)).thenReturn(Optional.empty());

        try {
            encurtadorService.getUrl(encurtada);
            fail("Expected NotFoundException, but no exception was thrown.");
        } catch (NotFoundException e) {
            // O teste passa se uma NotFoundException for lançada
        }
    }
}
