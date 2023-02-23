package com.example.sebitproje;

import com.example.sebitproje.KullaniciEntity;

public interface KullaniciService {
    KullaniciEntity getKullaniciById(Long id);
    KullaniciEntity saveKullanici(KullaniciEntity kullaniciEntity);
    KullaniciEntity updateKullanici(KullaniciEntity kullaniciEntity);
    void deleteKullanici(Long id);

}
