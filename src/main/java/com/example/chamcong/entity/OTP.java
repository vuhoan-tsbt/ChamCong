package com.example.chamcong.entity;

import com.example.chamcong.enumtation.OTPTypeEnum;
import com.example.chamcong.utils.StaticUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "otp")
public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;
    @Column(nullable = false)
    private OTPTypeEnum type;
    @Column(nullable = false)
    private int targetId;
    private String otp;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    protected String generateOTP(){
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < 6; i++){
            if(StaticUtils.randomInt(2) == 1){
                stringBuilder.append((char) (StaticUtils.randomInt(9)+ 48));
            }else{
                stringBuilder.append((char) (StaticUtils.randomInt(26) + 64));
            }
        }
        return stringBuilder.toString();
    }

    @PrePersist
    private void beforePersist(){
        this.setOtp(generateOTP());
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void beforeUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
