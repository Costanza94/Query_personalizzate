package com.example.Query.personalizzate.Entity;

import com.example.Query.personalizzate.Enumerated.CategoriaEnum;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prodotto")
public class ProdottoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descrizione")
    private String descrizione;
    @Column(name = "prezzo")
    private Double prezzo;
    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoriaEnum;
    @Column(name = "quantità_disponibile")
    private Integer quantitaDisponibile;
    @Column(name = "data_creazione")
    private LocalDate dataCreazione;


    private ProdottoEntity (){}

    public ProdottoEntity(LocalDate dataCreazione, CategoriaEnum categoriaEnum, Double prezzo, String nome, Integer id, String descrizione, Integer quantitaDisponibile) {
        this.dataCreazione = dataCreazione;
        this.categoriaEnum = categoriaEnum;
        this.prezzo = prezzo;
        this.nome = nome;
        this.id = id;
        this.descrizione = descrizione;
        this.quantitaDisponibile = quantitaDisponibile;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public Integer getQuantitaDisponibile() {
        return quantitaDisponibile;
    }

    public void setQuantitaDisponibile(Integer quantitaDisponibile) {
        this.quantitaDisponibile = quantitaDisponibile;
    }

    public CategoriaEnum getCategoriaEnum() {
        return categoriaEnum;
    }

    public void setCategoriaEnum(CategoriaEnum categoriaEnum) {
        this.categoriaEnum = categoriaEnum;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
