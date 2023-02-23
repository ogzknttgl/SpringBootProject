package com.example.sebitproje;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sebitproje.KullaniciEntity;
import com.example.sebitproje.KullaniciService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proje")
public class ProjeController {
    @Autowired
    ProjeService projeService;

    @GetMapping("/{id}")
    public ResponseEntity<ProjeEntity> getProjeById(@PathVariable Long id) {
        return new ResponseEntity<>(projeService.getProjeById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjeEntity> saveProje(@RequestBody ProjeEntity proje) {
        return new ResponseEntity<>(projeService.saveProje(proje), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProje(@PathVariable Long id) {
        projeService.deleteProje(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<ProjeEntity> updateProje(@RequestBody ProjeEntity proje) {
        return new ResponseEntity<>(projeService.updateProje(proje), HttpStatus.OK);
    }

    @PutMapping("/{proje_id}/{kullanici_id}")
    public ResponseEntity<ProjeEntity> addKullaniciToProje(@PathVariable Long proje_id, @PathVariable Long kullanici_id) {
        return new ResponseEntity<>(projeService.addKullaniciToProje(proje_id, kullanici_id), HttpStatus.OK);
    }

    @DeleteMapping("/{proje_id}/{kullanici_id}")
    public ResponseEntity<ProjeEntity> removeKullaniciFromProje(@PathVariable Long proje_id, @PathVariable Long kullanici_id) {
        return new ResponseEntity<>(projeService.removeKullaniciFromProje(proje_id, kullanici_id), HttpStatus.OK);
    }

}
