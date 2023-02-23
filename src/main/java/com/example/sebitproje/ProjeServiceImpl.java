package com.example.sebitproje;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.sebitproje.KullaniciEntity;
import com.example.sebitproje.KullaniciRepository;
import com.example.sebitproje.KullaniciService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ProjeServiceImpl implements ProjeService {
    private ProjeRepository projeRepository;


    @Override
    public ProjeEntity getProjeById(Long id) {
        return projeRepository.findById(id).get();
    }

    @Override
    public ProjeEntity saveProje(ProjeEntity projeEntity) {
        return projeRepository.save(projeEntity);
    }

    @Override
    public ProjeEntity updateProje(ProjeEntity projeEntity) {
        return projeRepository.save(projeEntity);
    }

    @Override
    public void deleteProje(Long id) {
        projeRepository.deleteById(id);
    }

    @Override
    public ProjeEntity addKullaniciToProje(Long proje_id, Long kullanici_id) {
        ProjeEntity projeEntity = projeRepository.findById(proje_id).get();
        KullaniciEntity kullaniciEntity = new KullaniciEntity();
        kullaniciEntity.setId(kullanici_id);
        projeEntity.getKullanicilar().add(kullaniciEntity);
        return projeRepository.save(projeEntity);
    }
    @Override
    public ProjeEntity removeKullaniciFromProje(Long proje_id, Long kullanici_id) {
        ProjeEntity projeEntity = projeRepository.findById(proje_id).get();
        KullaniciEntity kullaniciEntity = new KullaniciEntity();
        kullaniciEntity.setId(kullanici_id);
        projeEntity.getKullanicilar().remove(kullaniciEntity);
        return projeRepository.save(projeEntity);
    }
}

