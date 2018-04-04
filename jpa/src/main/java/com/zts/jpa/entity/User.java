package com.zts.jpa.entity;

import com.zts.jpa.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table
public class User extends BaseEntity{

    @NotNull
    @Column(unique = true)
    private String userName;

    @NotNull
    private String password;

    private Sex sex=Sex.SECRET;

}
