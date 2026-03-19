package com.ootd.fitme.domain.selectablevalue.entity;

import com.ootd.fitme.domain.attribute.entity.Attribute;
import com.ootd.fitme.domain.base.BaseDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "selectable_values")
public class SelectableValue extends BaseDeletableEntity {

    @JoinColumn(name = "attribute_id", nullable = false, unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Attribute attribute;

    @Column(name = "type", nullable = false, length = 100)
    private String type;

    private SelectableValue(String type, Attribute attribute) {
        this.type = type;
        this.attribute = attribute;
    }

    public static SelectableValue create(String type, Attribute attribute) {
        return new SelectableValue(type, attribute);
    }

}
