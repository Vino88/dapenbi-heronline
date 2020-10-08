package com.dapenbi.heronline.models;

import com.dapenbi.heronline.dto.RoleDto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="roles")
public class Roles extends BaseModel<RoleDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "role_name",nullable=false)
    @NotEmpty()
    private String name;

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

    @Override
    public void fromDto(RoleDto dto) {
        this.setId(dto.getId());
        this.setName(dto.getName());
    }

    @Override
    public RoleDto toDto() {
        RoleDto dto = new RoleDto();
        dto.setId(this.getId());
        dto.setName(this.getName());
        return null;
    }
}
