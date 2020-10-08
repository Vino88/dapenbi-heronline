package com.dapenbi.heronline.models;


import com.dapenbi.heronline.dto.KotaDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_M_KOTA")
public class Kota extends BaseModel<KotaDto> {

    @Id
    @Column(name = "id_kota")
    private Long id;

    @Column(name = "nama_kota")
    private String namaKota;

    public Kota() {
        this.provinsi = new Provinsi();
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_provinsi", nullable = false)
    private Provinsi provinsi;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_kota", referencedColumnName = "id_kota")
    private List<Kecamatan> kecamatan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    public Provinsi getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(Provinsi provinsi) {
        this.provinsi = provinsi;
    }

    @Override
    public void fromDto(KotaDto dto) {
        this.setId(dto.getId());
        this.setNamaKota(dto.getNamaKota());
        this.setActive(dto.isActive());
    }

    @Override
    public KotaDto toDto() {
        KotaDto dto = new KotaDto();
        dto.setId(this.getId());
        dto.setNamaKota(this.getNamaKota());
        dto.setActive(this.isActive());
        return dto;
    }
}
