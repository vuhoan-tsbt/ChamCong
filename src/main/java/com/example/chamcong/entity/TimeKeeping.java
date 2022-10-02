package com.example.chamcong.entity;

import com.example.chamcong.entity.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Accessors(chain=true)
@Table(name = "timekeeping")
public class TimeKeeping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    private Date months;

    private String radixCode;

}
