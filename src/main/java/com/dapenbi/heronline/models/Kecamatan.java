package com.dapenbi.heronline.models;


import com.dapenbi.heronline.dto.KecamatanDto;

import javax.persistence.*;

@Entity
@Table(name = "TBL_M_KECAMATAN")
public class Kecamatan extends BaseModel<KecamatanDto>{

    @Id
    @Column(name = "id_kecamatan")
    private Long id;

    @Column(name = "nama_kecamatan")
    private String namaKecamatan;

    public Kecamatan(){this.kota = new Kota();}

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_kota", nullable = false)
    private Kota kota;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_kecamatan", referencedColumnName = "id_kecamatan")
    private List<Kelurahan> kelurahan;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaKecamatan() {
        return namaKecamatan;
    }

    public void setNamaKecamatan(String namaKecamatan) {
        this.namaKecamatan = namaKecamatan;
    }

    /*public List<Kelurahan> getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(List<Kelurahan> kelurahan) {
        this.kelurahan = kelurahan;
    }*/

    public Kota getKota() {
        return kota;
    }

    public void setKota(Kota kota) {
        this.kota = kota;
    }

    @Override
    public void fromDto(KecamatanDto dto) {
        this.setId(dto.getId());
        this.setNamaKecamatan(dto.getNamaKecamatan());
        this.setActive(dto.isActive());
    }

    @Override
    public KecamatanDto toDto() {
        KecamatanDto dto = new KecamatanDto();
        dto.setId(this.getId());
        dto.setNamaKecamatan(this.getNamaKecamatan());
        dto.setActive(this.isActive());
        return dto;
    }


}
