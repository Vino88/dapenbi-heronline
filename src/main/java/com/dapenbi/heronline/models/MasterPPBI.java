package com.dapenbi.heronline.models;

import com.dapenbi.heronline.dto.MasterPPBIRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(exclude = "users")

@Entity
@Table(name = "TBL_R_PPBI")
public class MasterPPBI extends BaseModel<MasterPPBIRequest> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PPBI")
    private Integer id;

    @Column(name = "NAMA_PPBI", nullable = false)
    @NotEmpty
    private String name;

    @Column(name = "ALAMAT_SURAT1")
    private String alamatSurat1;

    @Column(name = "ALAMAT_SURAT2")
    private String alamatSurat2;

    @Column(name = "ALAMAT_SURAT3")
    private String alamatSurat3;

    @Column(name = "ALAMAT_SURAT4")
    private String alamatSurat4;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamatSurat1() {
        return alamatSurat1;
    }

    public void setAlamatSurat1(String alamatSurat1) {
        this.alamatSurat1 = alamatSurat1;
    }

    public String getAlamatSurat2() {
        return alamatSurat2;
    }

    public void setAlamatSurat2(String alamatSurat2) {
        this.alamatSurat2 = alamatSurat2;
    }

    public String getAlamatSurat3() {
        return alamatSurat3;
    }

    public void setAlamatSurat3(String alamatSurat3) {
        this.alamatSurat3 = alamatSurat3;
    }

    public String getAlamatSurat4() {
        return alamatSurat4;
    }

    public void setAlamatSurat4(String alamatSurat4) {
        this.alamatSurat4 = alamatSurat4;
    }

    @Override
    public void fromDto(MasterPPBIRequest dto) {
        this.setId(dto.getId());
        this.setName(dto.getName());
        this.setAlamatSurat1(dto.getAlamatSurat1());
        this.setAlamatSurat2(dto.getAlamatSurat2());
        this.setAlamatSurat3(dto.getAlamatSurat3());
        this.setAlamatSurat4(dto.getAlamatSurat4());

    }

    @Override
    public MasterPPBIRequest toDto() {
        MasterPPBIRequest dto = new MasterPPBIRequest();
        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setAlamatSurat1(this.getAlamatSurat1());
        dto.setAlamatSurat2(this.getAlamatSurat2());
        dto.setAlamatSurat3(this.getAlamatSurat3());
        dto.setAlamatSurat4(this.getAlamatSurat4());
        return dto;
    }
}
