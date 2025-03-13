package com.example.Query.personalizzate.Service;

import com.example.Query.personalizzate.Entity.ProdottoEntity;
import com.example.Query.personalizzate.Enumerated.CategoriaEnum;
import com.example.Query.personalizzate.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;

    //Create nuovo prodotto
    public ProdottoEntity creaProdotto(ProdottoEntity prodotto) {
        ProdottoEntity prodottoSaved = prodottoRepository.save(prodotto);
        return prodottoSaved;
    }

    //read all product
    public List<ProdottoEntity> readAllProduct() {
        return prodottoRepository.findAll();
    }

    //find by id
    public Optional<ProdottoEntity> findById(Long id) {
        //devo recuperare l'elemento dall'id tramite il metodo del repository findbyid
        Optional<ProdottoEntity> prodottoFound = prodottoRepository.findById(id);
        if (prodottoFound.isPresent()) {
            return prodottoFound;
        }
        return Optional.empty(); //Nel service utilizziamo Optional e mai ResponseEntity
    }

    //Update product
    public Optional<ProdottoEntity> updateProdotto(Long id, ProdottoEntity prodottoDatiDaAggiornare) {

        Optional<ProdottoEntity> prodottoTrovatoDaAggiornare = prodottoRepository.findById(id);

        if (prodottoTrovatoDaAggiornare.isPresent()) {
            prodottoTrovatoDaAggiornare.get().setDescrizione(prodottoDatiDaAggiornare.getDescrizione());
            prodottoTrovatoDaAggiornare.get().setNome(prodottoDatiDaAggiornare.getNome());
            prodottoTrovatoDaAggiornare.get().setPrezzo(prodottoDatiDaAggiornare.getPrezzo());
            prodottoTrovatoDaAggiornare.get().setDataCreazione(prodottoDatiDaAggiornare.getDataCreazione());
            prodottoTrovatoDaAggiornare.get().setCategoriaEnum(prodottoDatiDaAggiornare.getCategoriaEnum());
            prodottoTrovatoDaAggiornare.get().setQuantitaDisponibile(prodottoDatiDaAggiornare.getQuantitaDisponibile());

            ProdottoEntity prodottoAggiornatoDopoLaSave = prodottoRepository.save(prodottoTrovatoDaAggiornare.get());

            return Optional.of(prodottoAggiornatoDopoLaSave);
        }
        // se non troviamo il prodotto da aggiornare torniamo optional empty
        return Optional.empty();
    }

    //Delete by id
    public void deleteProdotto(Long id) {
        prodottoRepository.deleteById(id);
    }

    //Find by category
    public List<ProdottoEntity> findByCategory(CategoriaEnum categoriaEnum) {
        return prodottoRepository.findByCategoriaEnum(categoriaEnum);
    }


    //Find by name
    public List<ProdottoEntity> findByName(String nome) {
        return prodottoRepository.findByNomeContaining(nome);
    }

    // Find by prezzo minore di
    public List<ProdottoEntity> findByPriceLessThan(Double prezzo) {
        return prodottoRepository.findByPrezzoLessThan(prezzo);
    }

    //Desc Order
    public List<ProdottoEntity> findByPriceDesc(Double prezzo) {
        return prodottoRepository.findByPrezzoOrderByPrezzoDesc(prezzo);
    }
}
