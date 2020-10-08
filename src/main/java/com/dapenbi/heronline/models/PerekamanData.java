package com.dapenbi.heronline.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Immutable
@Table(name="vw_perekaman_data")
public class PerekamanData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "role_id")
    private Integer Role;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "ID_PPBI")
    private Integer idPPBI;

    @Column(name = "NIP", columnDefinition = "TEXT")
    private String nip;

    @Column(name = "nama_peserta")
    private String namaPeserta;

    @Column(name = "foto")
    private String foto;

    @Column(name = "ktp")
    private String ktp;

    @Column(name = "kk")
    private String kk;

    @Column(name = "surat_nikah")
    private String suratNikah;

    @Column(name = "kartu_pensiun")
    private String kartuPensiun;

    @Column(name = "sidik_jari")
    private String sidikJari;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "user_stamp",length = 200)
    private String userStamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIdPPBI() {
        return idPPBI;
    }

    public void setIdPPBI(Integer idPPBI) {
        this.idPPBI = idPPBI;
    }

    public Integer getRole() {
        return Role;
    }

    public void setRole(Integer role) {
        Role = role;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getKk() {
        return kk;
    }

    public void setKk(String kk) {
        this.kk = kk;
    }

    public String getSuratNikah() {
        return suratNikah;
    }

    public void setSuratNikah(String suratNikah) {
        this.suratNikah = suratNikah;
    }

    public String getKartuPensiun() {
        return kartuPensiun;
    }

    public void setKartuPensiun(String kartuPensiun) {
        this.kartuPensiun = kartuPensiun;
    }

    public String getSidikJari() {
        return sidikJari;
    }

    public void setSidikJari(String sidikJari) {
        this.sidikJari = sidikJari;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserStamp() {
        return userStamp;
    }

    public void setUserStamp(String userStamp) {
        this.userStamp = userStamp;
    }

    public String getNamaPeserta() {
        return namaPeserta;
    }

    public void setNamaPeserta(String namaPeserta) {
        this.namaPeserta = namaPeserta;
    }
}
