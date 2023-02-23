package com.example.sebitproje;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proje/katkilari")
public class ProjeKatkilariController {
    private final ProjeKatkilariService projeKatkilariService;

    public ProjeKatkilariController(ProjeKatkilariService projeKatkilariService) {
        this.projeKatkilariService = projeKatkilariService;
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<ProjeKatkilariEntity> createProjeKatkilari(@PathVariable Long projectId,
                                                                     @RequestBody ProjeKatkilariEntity projeKatkilari) {
        projeKatkilari = projeKatkilariService.createProjeKatkilari(projeKatkilari);
        return ResponseEntity.ok(projeKatkilari);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<List<ProjeKatkilariEntity>> getProjeKatkilari(@PathVariable Long projectId,
                                                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date commitDay,
                                                                        @RequestParam List<Long> kullaniciIds) {
        List<ProjeKatkilariEntity> projeKatkilariList = projeKatkilariService.getProjeKatkilari(projectId, commitDay, kullaniciIds);
        return new ResponseEntity<>(projeKatkilariList, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProjeKatkilariEntity>> getProjeKatkilari(@RequestParam Long projectId,
                                                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        // Check that the interval between startDate and endDate is at least 4 days
        LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (ChronoUnit.DAYS.between(start, end) < 4) {
            throw new IllegalArgumentException("The interval between start and end dates must be at least 4 days.");
        }
        List<ProjeKatkilariEntity> projeKatkilariList = projeKatkilariService.getUcGunUstuUcmeyenKullanicilar(projectId, startDate, endDate);

        return new ResponseEntity<>(projeKatkilariList, HttpStatus.OK);
    }
}

