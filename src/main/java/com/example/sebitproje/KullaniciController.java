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
@RequestMapping("/kullanici")
public class KullaniciController {
    @Autowired
    KullaniciService kullaniciService;

    @GetMapping("/{id}")
    public ResponseEntity<KullaniciEntity> getKullaniciById(@PathVariable Long id) {
        return new ResponseEntity<>(kullaniciService.getKullaniciById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<KullaniciEntity> saveKullanici(@RequestBody KullaniciEntity kullanici) {
        return new ResponseEntity<>(kullaniciService.saveKullanici(kullanici), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteKullanici(@PathVariable Long id) {
        kullaniciService.deleteKullanici(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity<KullaniciEntity> updateKullanici(@RequestBody KullaniciEntity kullanici) {
        return new ResponseEntity<>(kullaniciService.updateKullanici(kullanici), HttpStatus.OK);
    }
}
