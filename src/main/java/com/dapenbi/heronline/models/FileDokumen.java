package com.dapenbi.heronline.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Data
@Entity
@Table(name="TBL_T_DOKUMEN")
public class FileDokumen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Integer id;

    @Column(name = "user_id",nullable = false)
    @NotEmpty
    //user_id = username di table User
    private String userId;

    @Column(name = "path", nullable = false)
    @NotEmpty
    private String path;

    @Column(name = "doc_type",nullable = false)
    @NotEmpty
    // 1 = Foto, 2 = KTP , 3 = KK, 4 = Surat Nikah, 5 = Kartu Pensiun, 6 = Fingerprint
    private Integer docType;


}
