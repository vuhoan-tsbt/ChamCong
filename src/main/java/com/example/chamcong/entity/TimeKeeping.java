package com.example.chamcong.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain=true)
@Table(name = "timekeeping")
public class TimeKeeping  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    private String radixCode;

}
