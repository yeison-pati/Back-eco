package com.itm.ecosurprise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itm.ecosurprise.services.RepartidorService;

@RestController
@RequestMapping("/api/repartidores")
public class RepartidorController {
    @Autowired
    private RepartidorService repartidorService;


    @PostMapping("/{id}/completar-registro")
public ResponseEntity<?> completarRegistro(
    @PathVariable int id,
    @RequestParam("placa") String placa,
    @RequestParam("licencia") MultipartFile licencia,
    @RequestParam("soat") MultipartFile soat,
    @RequestParam("tecno") MultipartFile tecno
) {
    return repartidorService.completarRegistro(id, placa, licencia, soat, tecno);
}
}
