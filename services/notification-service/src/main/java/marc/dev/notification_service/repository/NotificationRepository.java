package marc.dev.notification_service.repository;

import marc.dev.notification_service.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
