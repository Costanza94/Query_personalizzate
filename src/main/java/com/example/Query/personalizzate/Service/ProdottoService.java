package com.example.Query.personalizzate.Service;

import com.example.Query.personalizzate.Entity.ProdottoEntity;
import com.example.Query.personalizzate.Enumerated.CategoriaEnum;
import com.example.Query.personalizzate.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Optional<ProdottoEntity> findById(Integer id) {
        //devo recuperare l'elemento dall'id tramite il metodo del repository findbyid
        Optional<ProdottoEntity> prodottoFound = prodottoRepository.findById(id);
        if (prodottoFound.isPresent()) {
            return prodottoFound;
        }
        return Optional.empty(); //Nel service utilizziamo Optional e mai ResponseEntity
    }

    //Update product
    public Optional<ProdottoEntity> updateProdotto(Integer id, ProdottoEntity prodottoDatiDaAggiornare) {
        Optional<ProdottoEntity> prodottoEntityOptional = prodottoRepository.findById(id);
        if (prodottoEntityOptional.isPresent()) {
            prodottoEntityOptional.get().setDescrizione(prodottoDatiDaAggiornare.getDescrizione());
            prodottoEntityOptional.get().setNome(prodottoDatiDaAggiornare.getNome());
            prodottoEntityOptional.get().setPrezzo(prodottoDatiDaAggiornare.getPrezzo());
            prodottoEntityOptional.get().setDataCreazione(prodottoDatiDaAggiornare.getDataCreazione());
            prodottoEntityOptional.get().setCategoriaEnum(prodottoDatiDaAggiornare.getCategoriaEnum());
            prodottoEntityOptional.get().setQuantitaDisponibile(prodottoDatiDaAggiornare.getQuantitaDisponibile());
            ProdottoEntity prodottoAggiornato = prodottoRepository.save(prodottoDatiDaAggiornare);
            return Optional.of(prodottoAggiornato);
        }
        return Optional.empty();
    }

    //Delete by id
    public void deleteById(Integer id) {
        prodottoRepository.deleteById(id);
    }

    //Find by category
    public List<ProdottoEntity> findByCategory(CategoriaEnum categoriaEnum) {
        List<ProdottoEntity> prodottoEntityOptional = prodottoRepository.findByCategoriaEnum(CategoriaEnum.VESTITI);
        return prodottoEntityOptional;
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
