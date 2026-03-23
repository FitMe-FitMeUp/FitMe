package com.ootd.fitme.domain.notiication.service;

import com.ootd.fitme.domain.notiication.entity.Notification;
import com.ootd.fitme.domain.notiication.entity.NotificationFactory;
import com.ootd.fitme.domain.notiication.repository.NotificationRepository;
import com.ootd.fitme.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {
    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NotificationFactory notificationFactory;

    @Mock
    private User user;

    @Mock
    private Notification notification;

    @InjectMocks
    private NotificationService notificationService;

    @Nested
    @DisplayName("알림 생성")
    class CreateNotificationTest {

        @Test
        @DisplayName("DM 알림을 생성하고 저장한다")
        void notifyDm() {
            String senderName = "보낸사람";
            String message = "안녕하세요";

            when(notificationFactory.dm(user, senderName, message)).thenReturn(notification);
            when(notificationRepository.save(notification)).thenReturn(notification);

            notificationService.notifyDm(user, senderName, message);

            verify(notificationFactory).dm(user, senderName, message);
            verify(notificationRepository).save(notification);
        }

        @Test
        @DisplayName("팔로우 알림을 생성하고 저장한다")
        void notifyFollowed() {
            String followerName = "팔로워";

            when(notificationFactory.followed(user, followerName)).thenReturn(notification);
            when(notificationRepository.save(notification)).thenReturn(notification);

            notificationService.notifyFollowed(user, followerName);

            verify(notificationFactory).followed(user, followerName);
            verify(notificationRepository).save(notification);
        }

        @Test
        @DisplayName("날씨 알림을 생성하고 저장한다")
        void notifyWeatherAlert() {
            String message = "비가 올 예정입니다.";

            when(notificationFactory.weatherAlert(user, message)).thenReturn(notification);
            when(notificationRepository.save(notification)).thenReturn(notification);

            notificationService.notifyWeatherAlert(user, message);

            verify(notificationFactory).weatherAlert(user, message);
            verify(notificationRepository).save(notification);
        }

        @Test
        @DisplayName("피드 좋아요 알림을 생성하고 저장한다")
        void notifyFeedLiked() {
            String likerName = "좋아요유저";

            when(notificationFactory.feedLiked(user, likerName)).thenReturn(notification);
            when(notificationRepository.save(notification)).thenReturn(notification);

            notificationService.notifyFeedLiked(user, likerName);

            verify(notificationFactory).feedLiked(user, likerName);
            verify(notificationRepository).save(notification);
        }

        @Test
        @DisplayName("피드 댓글 알림을 생성하고 저장한다")
        void notifyFeedCommented() {
            String commenterName = "댓글유저";
            String comment = "좋은 글이네요";

            when(notificationFactory.feedCommented(user, commenterName, comment)).thenReturn(notification);
            when(notificationRepository.save(notification)).thenReturn(notification);

            notificationService.notifyFeedCommented(user, commenterName, comment);

            verify(notificationFactory).feedCommented(user, commenterName, comment);
            verify(notificationRepository).save(notification);
        }

        @Test
        @DisplayName("팔로우한 사용자의 새 피드 알림을 생성하고 저장한다")
        void notifyFollowerNewFeed() {
            String writerName = "작성자";
            String feedName = "오늘의 코디";

            when(notificationFactory.followerNewFeed(user, writerName, feedName)).thenReturn(notification);
            when(notificationRepository.save(notification)).thenReturn(notification);

            notificationService.notifyFollowerNewFeed(user, writerName, feedName);

            verify(notificationFactory).followerNewFeed(user, writerName, feedName);
            verify(notificationRepository).save(notification);
        }

        @Test
        @DisplayName("속성 수정 알림을 생성하고 저장한다")
        void notifyAttributeUpdated() {
            String attributeName = "색상";

            when(notificationFactory.attributeUpdated(user, attributeName)).thenReturn(notification);
            when(notificationRepository.save(notification)).thenReturn(notification);

            notificationService.notifyAttributeUpdated(user, attributeName);

            verify(notificationFactory).attributeUpdated(user, attributeName);
            verify(notificationRepository).save(notification);
        }

        @Test
        @DisplayName("속성 추가 알림을 생성하고 저장한다")
        void notifyAttributeAdded() {
            String attributeName = "소재";

            when(notificationFactory.attributeAdded(user, attributeName)).thenReturn(notification);
            when(notificationRepository.save(notification)).thenReturn(notification);

            notificationService.notifyAttributeAdded(user, attributeName);

            verify(notificationFactory).attributeAdded(user, attributeName);
            verify(notificationRepository).save(notification);
        }

        @Test
        @DisplayName("속성 삭제 알림을 생성하고 저장한다")
        void notifyAttributeDeleted() {
            String attributeName = "사이즈";

            when(notificationFactory.attributeDeleted(user, attributeName)).thenReturn(notification);
            when(notificationRepository.save(notification)).thenReturn(notification);

            notificationService.notifyAttributeDeleted(user, attributeName);

            verify(notificationFactory).attributeDeleted(user, attributeName);
            verify(notificationRepository).save(notification);
        }
    }

    @Nested
    @DisplayName("알림 삭제")
    class DeleteNotificationTest {

        @Test
        @DisplayName("알림 ID로 삭제한다")
        void delete() {
            UUID notificationId = UUID.randomUUID();

            notificationService.delete(notificationId);

            verify(notificationRepository).deleteById(notificationId);
            verify(notificationFactory, never()).dm(any(), any(), any());
            verify(notificationRepository, never()).save(any());
        }
    }
}