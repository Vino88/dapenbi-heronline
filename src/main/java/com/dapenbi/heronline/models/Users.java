package com.dapenbi.heronline.models;

import com.dapenbi.heronline.dto.UsersDto;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name="TBL_R_USERS")
public class Users extends BaseModel<UsersDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer id;

    @Column(nullable = false)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @NotEmpty
    private String username;

    @Column(nullable = false, unique = true)
    @NotEmpty
    @Email(message = "{errors.invalid_email}")
    private String email;

    @Column(nullable=false)
    @NotEmpty
    @Size(min=4)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PPBI", nullable = false)
    private MasterPPBI masterPPBI;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Roles role;



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MasterPPBI getMasterPPBI() {
        return masterPPBI;
    }

    public void setMasterPPBI(MasterPPBI masterPPBI) {
        this.masterPPBI = masterPPBI;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public void fromDto(UsersDto dto) {
        this.setId(dto.getId());
        this.setEmail(dto.getEmail());
        this.setName(dto.getName());
        this.setUsername(dto.getUsername());
        this.setPassword(dto.getPassword());
        this.setActive(dto.isActive());
        this.setUserStamp(dto.getUserStamp());
    }

    @Override
    public UsersDto toDto() {
        UsersDto dto = new UsersDto();
        dto.setId(this.getId());
        dto.setEmail(this.getEmail());
        dto.setName(this.getName());
        dto.setUsername(this.getUsername());
        dto.setPassword(this.getPassword());
        dto.setActive(this.isActive());
        dto.setUserStamp(this.getUserStamp());
        return dto;
    }
}
