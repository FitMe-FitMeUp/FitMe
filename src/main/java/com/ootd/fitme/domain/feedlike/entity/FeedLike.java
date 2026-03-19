package com.ootd.fitme.domain.feedlike.entity;

import com.ootd.fitme.domain.base.BaseEntity;
import com.ootd.fitme.domain.feed.entity.Feed;
import com.ootd.fitme.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@Table(name = "feeds_likes")
public class FeedLike extends BaseEntity {

    @JoinColumn(name = "feed_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Feed feed;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    private FeedLike(Feed feed, User user) {
        this.feed = feed;
        this.user = user;
    }

    public static FeedLike create(Feed feed, User user) {
        return new FeedLike(feed, user);
    }


}
