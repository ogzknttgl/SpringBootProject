package com.example.sebitproje;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proje")
public class ProjeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "projectname", nullable = false)
    private String projectname;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "kullanici_proje",
            joinColumns = @JoinColumn(name = "proje_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "kullanici_id", referencedColumnName = "id"))
    private List<KullaniciEntity> kullanicilar;

    @OneToMany(mappedBy = "proje", cascade = CascadeType.ALL)
    private List<ProjeKatkilariEntity> projekatkilari;
    public ProjeEntity() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProjectname() {
        return projectname;
    }
    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public List<KullaniciEntity> getKullanicilar() {
        return kullanicilar;
    }
    public void setKullanicilar(List<KullaniciEntity> kullanicilar) {
        this.kullanicilar = kullanicilar;
    }

    public List<ProjeKatkilariEntity> getProjekatkilari() {
        return projekatkilari;
    }

    public void setProjekatkilari(List<ProjeKatkilariEntity> projekatkilari) {
        this.projekatkilari = projekatkilari;
    }
}
