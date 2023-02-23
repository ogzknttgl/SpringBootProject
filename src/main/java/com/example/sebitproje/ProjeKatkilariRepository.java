package com.example.sebitproje;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjeKatkilariRepository extends JpaRepository<ProjeKatkilariEntity, Long> {

    List<ProjeKatkilariEntity> findAllByProjeIdAndCommitDay(Long projectId, Date commitDay);

    List<ProjeKatkilariEntity> findAllByProjeIdAndCommitDayAndKullaniciIdIn(Long projectId, Date commitDay, List<Long> kullaniciIds);

    List<ProjeKatkilariEntity> findAllByProjeIdAndCommitDayBetween(Long projectId, Date startDate, Date endDate);

}
