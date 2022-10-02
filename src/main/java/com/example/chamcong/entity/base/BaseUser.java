package com.example.chamcong.entity.base;

import com.example.chamcong.entity.UserRole;
import com.example.chamcong.entity.base.BaseEntity;
import com.example.chamcong.enumtation.AccStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@MappedSuperclass
@SQLDelete(sql = "update user set deleted = false where id = ?")
public abstract class BaseUser extends BaseEntity implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    private AccStatusEnum status = AccStatusEnum.CREATED;

    protected abstract List<? extends UserRole> getRoles();


    @Override
    public String getUsername() {
        return String.valueOf(getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
