package com.example.Query.personalizzate.Repository;

import com.example.Query.personalizzate.Entity.ProdottoEntity;
import com.example.Query.personalizzate.Enumerated.CategoriaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<ProdottoEntity, Integer> {

    //queste sono custom query servono per fare le query con le where
    @Query
    List<ProdottoEntity> findByCategoriaEnum(CategoriaEnum categoriaEnum);
    @Query
    List<ProdottoEntity> findByNomeContaining(String nome);
    @Query
    List<ProdottoEntity> findByPrezzoLessThan(Double prezzo);
    @Query
    List<ProdottoEntity> findByPrezzoOrderByPrezzoDesc(Double prezzo);
}
