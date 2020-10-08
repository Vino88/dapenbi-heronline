package com.dapenbi.heronline.models.CompositeId;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class HerRegistrasiDetailPK implements Serializable {

    @Column(name = "tahun_her")
    private String tahunHer;
    @Column(name = "nip")
    private String nip;

    public HerRegistrasiDetailPK() {
    }

    public HerRegistrasiDetailPK(String tahunHer, String nip) {
        this.tahunHer = tahunHer;
        this.nip = nip;
    }

    public String getTahunHer() {
        return tahunHer;
    }

    public void setTahunHer(String tahunHer) {
        this.tahunHer = tahunHer;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
