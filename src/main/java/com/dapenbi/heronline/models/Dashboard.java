package com.dapenbi.heronline.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Immutable
@Table(name="vw_dashboard")
public class Dashboard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "total_peserta")
    private Integer totalPeserta;

    @Column(name = "total_sudah_her")
    private Integer totalSudahHer;

    @Column(name = "tahun_her")
    private String tahunHer;

    public Integer getTotalPeserta() {
        return totalPeserta;
    }

    public void setTotalPeserta(Integer totalPeserta) {
        this.totalPeserta = totalPeserta;
    }

    public Integer getTotalSudahHer() {
        return totalSudahHer;
    }

    public void setTotalSudahHer(Integer totalSudahHer) {
        this.totalSudahHer = totalSudahHer;
    }

    public String getTahunHer() {
        return tahunHer;
    }

    public void setTahunHer(String tahunHer) {
        this.tahunHer = tahunHer;
    }
}
