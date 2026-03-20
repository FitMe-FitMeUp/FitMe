package com.ootd.fitme.domain.clothes.entity;

import com.ootd.fitme.domain.base.BaseUpdateEntity;
import com.ootd.fitme.domain.clothes.enums.ClothesType;
import com.ootd.fitme.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "clothes")
public class Clothes extends BaseUpdateEntity {

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "image_url", length = 1000)
    private String imageUrl;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClothesType clothesType;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    private Clothes(String name, ClothesType clothesType, String imageUrl, User user) {
        this.name = name;
        this.clothesType = clothesType;
        this.imageUrl = imageUrl;
        this.user = user;

    }

    public static Clothes create(String name, ClothesType clothesType, User user) {
        return new Clothes(name, clothesType, null, user);
    }

    public static Clothes createWithImage(String name, ClothesType clothesType, User user, String imageUrl) {
        return new Clothes(name, clothesType, imageUrl, user);
    }


}
