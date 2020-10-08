package com.dapenbi.heronline.dto;

import javax.validation.constraints.NotEmpty;

public class PerekamanDataRequest {
    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private String Foto;

    private String KK;

    private String KTP;

    private String kartuPensiun;

    private String suratNikah;
}
