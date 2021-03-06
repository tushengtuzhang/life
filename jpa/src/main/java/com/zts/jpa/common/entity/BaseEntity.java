package com.zts.jpa.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private Status status=Status.ACTIVE;

    @Transient
    private Date startTime;

    @Transient
    private Date endTime;

}


