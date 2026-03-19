package com.ootd.fitme.domain.feedclothes.entity;

import com.ootd.fitme.domain.base.BaseEntity;
import com.ootd.fitme.domain.clothes.entity.Clothes;
import com.ootd.fitme.domain.feed.entity.Feed;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "feeds_clothes")
public class FeedClothes extends BaseEntity {

    @JoinColumn(name = "feed_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Feed feed;

    @JoinColumn(name = "clothes_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Clothes clothes;

    private FeedClothes(Feed feed, Clothes clothes) {
        this.feed = feed;
        this.clothes = clothes;
    }

    public static FeedClothes create(Feed feed, Clothes clothes) {
        return new FeedClothes(feed, clothes);
    }


}
