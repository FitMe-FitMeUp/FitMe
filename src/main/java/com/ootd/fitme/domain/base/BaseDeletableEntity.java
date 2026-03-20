package com.ootd.fitme.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseDeletableEntity extends BaseUpdateEntity {

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    public void delete() {
        isDeleted = true;
    }
    public void restore() {
        isDeleted = false;
    }

}
