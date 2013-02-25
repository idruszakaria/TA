package com.artivisi.school.onlinetest.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name="t_soal")
public class Soal {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_topik")
    private Topik topik;

    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String kode;
    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String nama;

    @NotNull
    @Min(1)
    @Column(nullable = false, name = "jumlah_pertanyaan")
    private Integer jumlahPertanyaan;
    @Min(0)
    @Column(nullable = false, name = "nilai_lulus_minimum")
    private Integer nilaiLulusMinimum;
    @Min(0)
    @Column(nullable = false, name = "tingkat_kesulitan")
    private Integer tingkatKesulitan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Topik getTopik() {
        return topik;
    }

    public void setTopik(Topik topik) {
        this.topik = topik;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getJumlahPertanyaan() {
        return jumlahPertanyaan;
    }

    public void setJumlahPertanyaan(Integer jumlahPertanyaan) {
        this.jumlahPertanyaan = jumlahPertanyaan;
    }

    public Integer getNilaiLulusMinimum() {
        return nilaiLulusMinimum;
    }

    public void setNilaiLulusMinimum(Integer nilaiLulusMinimum) {
        this.nilaiLulusMinimum = nilaiLulusMinimum;
    }

    public Integer getTingkatKesulitan() {
        return tingkatKesulitan;
    }

    public void setTingkatKesulitan(Integer tingkatKesulitan) {
        this.tingkatKesulitan = tingkatKesulitan;
    }
}
