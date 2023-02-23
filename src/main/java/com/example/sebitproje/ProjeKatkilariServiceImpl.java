package com.example.sebitproje;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjeKatkilariServiceImpl implements ProjeKatkilariService {

    private final ProjeKatkilariRepository projeKatkilariRepository;
    private final ProjeRepository projeRepository;
    private final KullaniciRepository kullaniciRepository;

    public ProjeKatkilariServiceImpl(ProjeKatkilariRepository projeKatkilariRepository,
                                     ProjeRepository projeRepository,
                                     KullaniciRepository kullaniciRepository) {
        this.projeKatkilariRepository = projeKatkilariRepository;
        this.projeRepository = projeRepository;
        this.kullaniciRepository = kullaniciRepository;
    }

    @Override
    public ProjeKatkilariEntity createProjeKatkilari(ProjeKatkilariEntity projeKatkilari) {
        ProjeEntity proje = projeRepository.findById(projeKatkilari.getProje().getId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + projeKatkilari.getProje().getId()));

        KullaniciEntity kullanici = kullaniciRepository.findById(projeKatkilari.getKullanici().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + projeKatkilari.getKullanici().getId()));

        projeKatkilari.setProje(proje);
        projeKatkilari.setKullanici(kullanici);

        return projeKatkilariRepository.save(projeKatkilari);
    }

    @Override
    public List<ProjeKatkilariEntity> getProjeKatkilari(Long projectId, Date commitDay, List<Long> kullaniciIds) {
        ProjeEntity proje = projeRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + projectId));

        List<ProjeKatkilariEntity> projeKatkilariList;
        if (kullaniciIds != null && !kullaniciIds.isEmpty()) {
            projeKatkilariList = projeKatkilariRepository.findAllByProjeIdAndCommitDayAndKullaniciIdIn(projectId, commitDay, kullaniciIds);
        } else {
            projeKatkilariList = projeKatkilariRepository.findAllByProjeIdAndCommitDay(projectId, commitDay);
        }
        return projeKatkilariList;
    }
    @Override
    public List<ProjeKatkilariEntity> getUcGunUstuUcmeyenKullanicilar(Long projectId, Date startDate, Date endDate) {
        // Verilen tarih aralığı en az 4 gün olmalı
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        if (diffInDays < 3) {
            throw new IllegalArgumentException("Tarih aralığı en az 4 gün olmalı");
        }

        // Proje ve katkı listesini al
        ProjeEntity proje = projeRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + projectId));
        List<ProjeKatkilariEntity> projeKatkilariList = projeKatkilariRepository.findAllByProjeIdAndCommitDayBetween(projectId, startDate, endDate);

        // Kullanıcıları al
        List<KullaniciEntity> kullaniciList = (List<KullaniciEntity>) kullaniciRepository.findAll();

        // Kullanıcıları grupla ve filtrele
        List<ProjeKatkilariEntity> sonuc = kullaniciList.stream()
                .filter(kullanici -> projeKatkilariList.stream()
                        .filter(pk -> pk.getKullanici().equals(kullanici))
                        .map(ProjeKatkilariEntity::getCommitDay)
                        .distinct()
                        .count() >= 3)
                .map(kullanici -> {
                    ProjeKatkilariEntity projeKatkilari = new ProjeKatkilariEntity();
                    projeKatkilari.setProje(proje);
                    projeKatkilari.setKullanici(kullanici);
                    projeKatkilari.setCommitCount(0);
                    return projeKatkilari;
                })
                .collect(Collectors.toList());

        return sonuc;
    }
}


