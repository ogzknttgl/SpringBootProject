package com.example.sebitproje;

import com.example.sebitproje.ProjeEntity;
public interface ProjeService {
    ProjeEntity getProjeById(Long id);
    ProjeEntity saveProje(ProjeEntity projeEntity);
    ProjeEntity updateProje(ProjeEntity projeEntity);
    void deleteProje(Long id);
    ProjeEntity addKullaniciToProje(Long proje_id, Long kullanici_id);

    ProjeEntity removeKullaniciFromProje(Long proje_id, Long kullanici_id);

}

