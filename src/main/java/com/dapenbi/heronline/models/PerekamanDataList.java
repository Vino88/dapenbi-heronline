package com.dapenbi.heronline.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Immutable
@Table(name="vw_perekaman_list")
public class PerekamanDataList implements Serializable {

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

    @Column(name = "finger1")
    private Integer finger1;

    @Column(name = "finger2")
    private Integer finger2;

    @Column(name = "finger3")
    private Integer finger3;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole() {
        return Role;
    }

    public void setRole(Integer role) {
        Role = role;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getFinger1() {
        return finger1;
    }

    public void setFinger1(Integer finger1) {
        this.finger1 = finger1;
    }

    public Integer getFinger2() {
        return finger2;
    }

    public void setFinger2(Integer finger2) {
        this.finger2 = finger2;
    }

    public Integer getFinger3() {
        return finger3;
    }

    public void setFinger3(Integer finger3) {
        this.finger3 = finger3;
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
}
