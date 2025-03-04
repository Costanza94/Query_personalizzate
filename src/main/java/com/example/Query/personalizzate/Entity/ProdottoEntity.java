package com.example.Query.personalizzate.Entity;

import com.example.Query.personalizzate.Enumerated.CategoriaEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    //per non passare la data nel json perchè andiamo a settarla nel costruttore
    //siccome è la data di creazione dell'oggetto la settiamo con la data corrente direttamente
    // così non la passiamo tramite il json
    //(ovviamente non vale per tutte le date ma solo quando  creaiamo)
    @JsonIgnore
    @Column(name = "data_creazione")
    private LocalDate dataCreazione = LocalDate.now();


    private ProdottoEntity (){}

    public ProdottoEntity(Integer id, String nome, String descrizione, Double prezzo, CategoriaEnum categoriaEnum, Integer quantitaDisponibile) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.categoriaEnum = categoriaEnum;
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
