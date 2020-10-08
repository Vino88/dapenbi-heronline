package com.dapenbi.heronline.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MenuDto {
    private Integer id;

    @NotEmpty()
    private String name;

    private String url;

    private String grupMenu;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGrupMenu() {
        return grupMenu;
    }

    public void setGrupMenu(String grupMenu) {
        this.grupMenu = grupMenu;
    }
}
