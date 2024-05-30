package ai.stunner.vetaforge.service.feign;

import ai.stunner.vetaforge.client.AuthorizedFeignClient;
import ai.stunner.vetaforge.service.dto.aisp.AispDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@AuthorizedFeignClient(name = "psd2", fallback = Psd2ServiceFallback.class)
public interface Psd2Service {

    @GetMapping(value = "/api/psd2/microservice/aisp/{ownerId}")
    AispDTO getAisp(@RequestHeader("microservice_key") String microserviceKey, @PathVariable("ownerId") String ownerId);
}
