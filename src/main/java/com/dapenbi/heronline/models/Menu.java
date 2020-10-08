package com.dapenbi.heronline.models;

import com.dapenbi.heronline.dto.MenuDto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="menu")
public class Menu extends BaseModel<MenuDto>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Integer id;

    @Column(name = "menu_name",nullable=false)
    @NotEmpty()
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "menu_parent_grup", nullable = false)
    private String grupMenu;

    @Column(name = "menu_parent_sort", nullable = false)
    private Integer grupMenuSort;

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

    public Integer getGrupMenuSort() {
        return grupMenuSort;
    }

    public void setGrupMenuSort(Integer grupMenuSort) {
        this.grupMenuSort = grupMenuSort;
    }

    @Override
    public void fromDto(MenuDto dto) {
        this.setId(dto.getId());
        this.setName(dto.getName());
        this.setGrupMenu(dto.getGrupMenu());
        this.setUrl(dto.getUrl());
    }

    @Override
    public MenuDto toDto() {
        MenuDto dto = new MenuDto();
        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setUrl(this.getUrl());
        dto.setGrupMenu(this.getGrupMenu());
        return dto;
    }
}
