package com.example.Query.personalizzate.Controller;

import com.example.Query.personalizzate.Entity.ProdottoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {
    @Autowired
    ProdottoEntity prodotto;


//    @GetMapping("/select-all")
//    public ResponseEntity<ArrayList<ProdottoEntity>> taskList() {
//        return ResponseEntity.ok(prodotto.getAllTasks());
}
