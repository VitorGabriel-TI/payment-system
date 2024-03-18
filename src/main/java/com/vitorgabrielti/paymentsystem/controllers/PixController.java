package com.vitorgabrielti.paymentsystem.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vitorgabrielti.paymentsystem.dtos.PixChargeRequestDTO;
import com.vitorgabrielti.paymentsystem.services.PixService;

public class PixController {
    
    @Autowired
    private PixService pixService;

    @GetMapping
    public ResponseEntity pixCreateEVP(){

        JSONObject response = this.pixService.pixCreateEVP();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.toString());
    }

    @PostMapping
    public ResponseEntity pixCreateCharge(@RequestBody PixChargeRequestDTO pixChargeRequestDTO){
        JSONObject response = this.pixService.pixCreateCharge(pixChargeRequestDTO);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.toString());
    }
}
