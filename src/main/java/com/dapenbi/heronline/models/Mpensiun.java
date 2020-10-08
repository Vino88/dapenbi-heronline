package com.dapenbi.heronline.models;

import com.dapenbi.heronline.dto.MPensiunDto;
import javax.persistence.*;

@Entity
@Table(name = "TBL_M_PENSIUN")
public class Mpensiun extends BaseModel<MPensiunDto>{

    @Id
    private String nip;

    @Column(name = "NAMA_PESERTA", length = 50)
    private String namaPeserta;

    @Column(name = "ID_PENERIMA_MP", nullable = false)
    private String idPenerimaMP;

    @Column(name = "NAMA_PENERIMA_MP", length = 50)
    private String namaPenerimaMp;

    @Column(name = "ALAMAT_LENGKAP", length = 100)
    private String alamatLengkap;

    @Column(name = "RT")
    private String rt;

    @Column(name = "RW")
    private String rw;

    @Column(name = "NO_TLP")
    private String noTelp;

    @Column(name = "NO_HP")
    private String noHp;

    private String email;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaPeserta() {
        return namaPeserta;
    }

    public void setNamaPeserta(String namaPeserta) {
        this.namaPeserta = namaPeserta;
    }

    public String getIdPenerimaMP() {
        return idPenerimaMP;
    }

    public void setIdPenerimaMP(String idPenerimaMP) {
        this.idPenerimaMP = idPenerimaMP;
    }

    public String getNamaPenerimaMp() {
        return namaPenerimaMp;
    }

    public void setNamaPenerimaMp(String namaPenerimaMp) {
        this.namaPenerimaMp = namaPenerimaMp;
    }

    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkap = alamatLengkap;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void fromDto(MPensiunDto dto) {
        this.setNip(dto.getNip());
        this.setNamaPeserta(dto.getNamaPeserta());
        this.setIdPenerimaMP(dto.getIdPenerimaMP());
        this.setNamaPenerimaMp(dto.getNamaPenerimaMp());
        this.setAlamatLengkap(dto.getAlamatLengkap());
        this.setRt(dto.getRt());
        this.setRw(dto.getRw());
        this.setNoHp(dto.getNoHp());
        this.setNoTelp(dto.getNoTelp());
        this.setEmail(dto.getEmail());
    }

    @Override
    public MPensiunDto toDto() {

        MPensiunDto dto = new MPensiunDto();

        dto.setNip(this.getNip());
        dto.setNamaPeserta(this.getNamaPeserta());
        dto.setIdPenerimaMP(this.getIdPenerimaMP());
        dto.setNamaPenerimaMp(this.getNamaPenerimaMp());
        dto.setAlamatLengkap(this.getAlamatLengkap());
        dto.setRt(this.getRt());
        dto.setRw(this.getRw());
        dto.setNoHp(this.getNoHp());
        dto.setNoTelp(this.getNoTelp());
        dto.setEmail(this.getEmail());

        return dto;
    }
}
