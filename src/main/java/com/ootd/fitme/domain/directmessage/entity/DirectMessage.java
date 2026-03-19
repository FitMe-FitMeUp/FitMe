package com.ootd.fitme.domain.directmessage.entity;

import com.ootd.fitme.domain.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "direct_messages")
public class DirectMessage extends BaseEntity {

    @Column(name = "sender_id", nullable = false)
    private UUID senderId;

    @Column(name = "receiver_id", nullable = false)
    private UUID receiverId;

    @Column(name = "content", nullable = false)
    private String content;

    private DirectMessage(UUID senderId, UUID receiverId, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    public static DirectMessage of(UUID senderId, UUID receiverId, String content) {
        return new DirectMessage(senderId, receiverId, content);
    }

}
