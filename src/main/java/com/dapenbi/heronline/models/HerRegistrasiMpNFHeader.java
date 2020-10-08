package com.dapenbi.heronline.models;


import com.dapenbi.heronline.dto.HerRegistrasiMpNFHeaderDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_T_HER_REGISTRASI_H_NF")
public class HerRegistrasiMpNFHeader extends BaseModel<HerRegistrasiMpNFHeaderDto>{
    @Id
    @Column(name = "TAHUN_HER", length = 4)
    private String tahunHer;
    @Column(name = "STATUS_HER", length = 20)
    private String statusHer;
    @Column(name = "TOTAL_HER", length = 22)
    private Long totalHer;
    @Column(name = "USER_STAMP", length = 100)
    private String userStamp;

    @OneToMany
    @JoinColumn(name = "TAHUN_HER")
    private List<HerRegistrasiMpNFDetail> herRegistrasiMpNFDetails;

    public List<HerRegistrasiMpNFDetail> getHerRegistrasiMpNFDetails() {
        return herRegistrasiMpNFDetails;
    }

    public void setHerRegistrasiMpNFDetails(List<HerRegistrasiMpNFDetail> herRegistrasiMpNFDetails) {
        this.herRegistrasiMpNFDetails = herRegistrasiMpNFDetails;
    }

    public String getTahunHer() {
        return tahunHer;
    }

    public void setTahunHer(String tahunHer) {
        this.tahunHer = tahunHer;
    }

    public String getStatusHer() {
        return statusHer;
    }

    public void setStatusHer(String statusHer) {
        this.statusHer = statusHer;
    }

    public Long getTotalHer() {
        return totalHer;
    }

    public void setTotalHer(Long totalHer) {
        this.totalHer = totalHer;
    }

    @Override
    public String getUserStamp() {
        return userStamp;
    }

    @Override
    public void setUserStamp(String userStamp) {
        this.userStamp = userStamp;
    }



    @Override
    public void fromDto(HerRegistrasiMpNFHeaderDto dto) {
        this.setTahunHer(dto.getTahunHer());
        this.setStatusHer(dto.getStatusHer());
        this.setTotalHer(dto.getTotalHer());
        this.setUserStamp(dto.getUserStamp());
    }

    @Override
    public HerRegistrasiMpNFHeaderDto toDto() {

        HerRegistrasiMpNFHeaderDto dto = new HerRegistrasiMpNFHeaderDto();
        dto.setTahunHer(this.getTahunHer());
        dto.setStatusHer(this.getStatusHer());
        dto.setTotalHer(this.getTotalHer());
        dto.setUserStamp(this.getUserStamp());

        return dto;
    }
}
