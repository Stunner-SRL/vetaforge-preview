package ai.stunner.vetaforge.service.feign;

import ai.stunner.vetaforge.client.AuthorizedFeignClient;
import ai.stunner.vetaforge.domain.NotificationsDTO;
import ai.stunner.vetaforge.domain.NotificationsFacadeDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Set;

@AuthorizedFeignClient(name = "inappnotifications")
public interface InAppNotificationsService {

    @PostMapping(value = "/api/send-notifications")
//    @Async
    NotificationsDTO sendNotification(@Valid @RequestBody NotificationsFacadeDTO NotificationsFacadeDTO, @RequestParam("channels") Set<String> channels);
}
