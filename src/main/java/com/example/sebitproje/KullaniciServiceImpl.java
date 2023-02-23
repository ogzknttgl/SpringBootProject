package com.example.sebitproje;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.sebitproje.KullaniciEntity;
import com.example.sebitproje.KullaniciRepository;
import com.example.sebitproje.KullaniciService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class KullaniciServiceImpl implements KullaniciService{

    KullaniciRepository kullaniciRepository;

    @Override
    public KullaniciEntity getKullaniciById(Long id) {
        return kullaniciRepository.findById(id).get();
    }
    @Override
    public KullaniciEntity saveKullanici(KullaniciEntity kullaniciEntity) {
        return kullaniciRepository.save(kullaniciEntity);
    }
    @Override
    public KullaniciEntity updateKullanici(KullaniciEntity kullaniciEntity) {
        return kullaniciRepository.save(kullaniciEntity);
    }
    @Override
    public void deleteKullanici(Long id) {
        kullaniciRepository.deleteById(id);
    }
}
