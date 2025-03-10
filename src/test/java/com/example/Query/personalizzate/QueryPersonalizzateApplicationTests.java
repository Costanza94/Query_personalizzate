package com.example.Query.personalizzate;

import com.example.Query.personalizzate.Controller.ProdottoController;
import com.example.Query.personalizzate.Entity.ProdottoEntity;
import com.example.Query.personalizzate.Enumerated.CategoriaEnum;
import com.example.Query.personalizzate.Service.ProdottoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class QueryPersonalizzateApplicationTests {

    @Autowired
    private ProdottoController prodottoController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProdottoService prodottoService;

    private ProdottoEntity prodotto;
    private ProdottoEntity prodottoNotFound;

    @BeforeEach
    public void setUp() {

        prodotto = new ProdottoEntity();

        prodotto.setId(3L);

        prodotto.setNome("Test Prodotto");

        prodotto.setCategoriaEnum(CategoriaEnum.ELETTRONICA);

        prodotto.setPrezzo(99.00);

        prodotto.setDescrizione("Descrizione test");

        prodottoNotFound = new ProdottoEntity();

        prodottoNotFound.setId(7L);

        prodottoNotFound.setNome("Test Prodotto0");

        prodottoNotFound.setCategoriaEnum(CategoriaEnum.ELETTRONICA);

        prodottoNotFound.setPrezzo(95.00);

        prodottoNotFound.setDescrizione("Descrizione test0");
    }

    @Test
    public void testCreaProdotto() throws Exception {
        // Step 1: prima di testare andiamo a memorizzare un prodotto di test utilizzando direttamente il service
        // per salvare qualsiasi oggetto si utilizza il when con questa chiamata
        when(prodottoService.creaProdotto(any(ProdottoEntity.class))).thenReturn(prodotto);
        // Step 2: tramite MockMvc andiamo a richiamare il controller usando il metodo create e controlliamo che il
        // risultato del controllerCreate sia uguale al risultato dell'oggetto di test che abbiamo memorizzato in precedenza
        mockMvc.perform(post("/prodotto/crea")// chiamo l'URL del controller
                        .contentType(MediaType.APPLICATION_JSON) // setto il tipo
                        .content(objectMapper.writeValueAsString(prodotto))) // controlliamo che il Json di ritorno sia uguale
                // al json dell'oggetto che abbiamo creato
                .andDo(print())
                .andExpect(status().isOk())// controlliamo che il codice status sia ok
                .andExpect(jsonPath("$.nome").value("Test Prodotto")); // controllo opzionale
    }

    @Test
    //Questo metodo può generare delle eccezioni, quindi è dichiarato con throws Exception.
    public void testReadAllProducts() throws Exception {
        //Qui si sta utilizzando un mock (un oggetto finto) per simulare il comportamento del servizio prodottoService
        when(prodottoService.readAllProduct()).thenReturn(Collections.singletonList(prodotto));
        //Questa riga simula una richiesta HTTP GET all'endpoint /prodotto/select-all. mockMvc è un oggetto
        // che consente di testare le chiamate HTTP senza dover effettivamente avviare un server.
        mockMvc.perform(get("/prodotto/select-all"))
                //Questa riga dice di stampare il risultato della risposta della richiesta HTTP nel log del test
                .andDo(print())  //Qui si verifica che la risposta alla richiesta HTTP abbia uno stato "OK" (cioè HTTP 200),
                // che significa che la richiesta è stata elaborata correttamente.
                .andExpect(status().isOk());
    }
    @Test
    public void testTrovaPerId() throws Exception{
        when(prodottoService.findById(anyLong())).thenReturn(Optional.of(prodotto));
    }
}
