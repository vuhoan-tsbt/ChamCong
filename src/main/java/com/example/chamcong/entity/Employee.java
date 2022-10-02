package com.example.chamcong.entity;

import com.example.chamcong.entity.Manager;
import com.example.chamcong.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Employee extends User {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonIgnore

    private Manager manager;

    private String password = "system.com";

}
