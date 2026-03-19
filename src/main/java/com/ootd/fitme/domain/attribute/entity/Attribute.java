package com.ootd.fitme.domain.attribute.entity;

import com.ootd.fitme.domain.base.BaseUpdateEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "attributes")
public class Attribute extends BaseUpdateEntity {

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;


    private Attribute(String name) {
        this.name = name;
    }

    public static Attribute create(String name) {
        return new Attribute(name);
    }
}
