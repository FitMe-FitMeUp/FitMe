package com.ootd.fitme.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@MappedSuperclass
public class BaseUpdateEntity extends BaseEntity {

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

}
