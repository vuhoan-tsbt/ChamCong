package com.example.chamcong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "position")
@NoArgsConstructor
@AllArgsConstructor

public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    private String position;

    private int wage;

    private long salary;

    private long allowance;
}
