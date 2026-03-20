package com.ootd.fitme.domain.comment.entity;

import com.ootd.fitme.domain.base.BaseEntity;
import com.ootd.fitme.domain.feed.entity.Feed;
import com.ootd.fitme.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Column(name = "content", nullable = false)
    private String content;

    @JoinColumn(name = "feed_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Feed feed;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    private Comment(String content, Feed feed, User user) {
        this.content = content;
        this.feed = feed;
        this.user = user;
    }

    public static Comment create(String content, Feed feed, User user) {
        return new Comment(content, feed, user);
    }


}
