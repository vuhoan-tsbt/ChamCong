package com.example.chamcong.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@MappedSuperclass
@Where(clause = "deleted = false")
public abstract class BaseEntity {
    @JsonIgnore
    private boolean deleted = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @JsonIgnore
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "created_by")
    @JsonIgnore
    private int createdBy;

    @Column(name = "updated_by")
    @JsonIgnore
    private int updatedBy;

    @PrePersist
    private void prePersist() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof BaseUser) {
            this.createdBy = ((BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            this.updatedBy = ((BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        }
    }

    @PreUpdate
    private void preUpdate() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof BaseUser) {
            this.updatedAt = LocalDateTime.now();
            this.updatedBy = ((BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        }
    }
}
