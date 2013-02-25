package com.artivisi.school.onlinetest.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="t_ujian")
public class Ujian {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String id;
    
    @NotNull
    @Column(nullable = false, name = "waktu_mulai")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuMulai;

    @NotNull
    @Column(nullable = false, name = "waktu_selesai")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuSelesai;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_peserta")
    private Peserta peserta;
    
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_pertanyaan")
    private Pertanyaan pertanyaan;
    
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_pilihan")
    private Pilihan pilihan;
    
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_ujian")
    private Ujian ujian;

    public Pertanyaan getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(Pertanyaan pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public Pilihan getPilihan() {
        return pilihan;
    }

    public void setPilihan(Pilihan pilihan) {
        this.pilihan = pilihan;
    }

    public Ujian getUjian() {
        return ujian;
    }

    public void setUjian(Ujian ujian) {
        this.ujian = ujian;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(Date waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public Date getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(Date waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public Peserta getPeserta() {
        return peserta;
    }

    public void setPeserta(Peserta peserta) {
        this.peserta = peserta;
    }
}
