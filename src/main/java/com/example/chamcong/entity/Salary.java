package com.example.chamcong.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;


    @Column(name="salary_for_one_hour_work")
    private long salaryForOneHourWork;

    @Column(name = "total_working_hours")
    private long totalWorkingHours;

    @Column(name="total_salary")
    private long totalSalary;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name="user_id")
    private User user;
}
