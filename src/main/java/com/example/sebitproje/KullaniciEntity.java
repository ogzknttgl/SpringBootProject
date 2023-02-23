package com.example.sebitproje;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kullanici")
public class KullaniciEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "salary", nullable = false)
    private Double salary;
    @ManyToMany(mappedBy = "kullanicilar")
    private List<ProjeEntity> projeler = new ArrayList<>();

    @OneToMany(mappedBy = "kullanici", cascade = CascadeType.ALL)
    private List<ProjeKatkilariEntity> projekatkilari;

    public KullaniciEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<ProjeEntity> getProjeler() {
        return projeler;
    }

    public void setProjeler(List<ProjeEntity> projeler) {
        this.projeler = projeler;
    }

    public List<ProjeKatkilariEntity> getProjekatkilari() {
        return projekatkilari;
    }

    public void setProjekatkilari(List<ProjeKatkilariEntity> projekatkilari) {
        this.projekatkilari = projekatkilari;
    }
}
