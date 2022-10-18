package com.example.chamcong.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "timekeeping_details")
public class TimeKeepingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @OneToOne
    @JoinColumn(name="timekeeping_id")
    private TimeKeeping timeKeeping;

    private LocalDateTime entryTime;

    private LocalDateTime timeout;


}
