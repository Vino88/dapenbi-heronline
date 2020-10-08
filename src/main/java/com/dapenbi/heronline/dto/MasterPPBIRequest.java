package com.dapenbi.heronline.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class MasterPPBIRequest {

    private Integer id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String alamatSurat1;

    private String alamatSurat2;
    private String alamatSurat3;
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
}
