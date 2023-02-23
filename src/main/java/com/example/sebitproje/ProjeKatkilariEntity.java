package com.example.sebitproje;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proje_katkilari")
public class ProjeKatkilariEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    private ProjeEntity proje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullaniciId", referencedColumnName = "id")
    private KullaniciEntity kullanici;

    @Column(name = "commitDay", columnDefinition = "DATE")
    private Date commitDay;

    @Column(name = "commit_count")
    private Integer commitCount;

}
