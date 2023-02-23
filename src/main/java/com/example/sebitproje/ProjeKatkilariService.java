package com.example.sebitproje;

import java.util.Date;
import java.util.List;

public interface ProjeKatkilariService {
    ProjeKatkilariEntity createProjeKatkilari(ProjeKatkilariEntity projeKatkilari);
    List<ProjeKatkilariEntity> getProjeKatkilari(Long projectId, Date commitDay, List<Long> kullaniciIds);

    List<ProjeKatkilariEntity> getUcGunUstuUcmeyenKullanicilar(Long projectId, Date startDate, Date endDate);
}