package ai.stunner.vetaforge.service.feign;

import ai.stunner.vetaforge.service.dto.aisp.AispDTO;
import org.springframework.stereotype.Component;

@Component
public class Psd2ServiceFallback implements Psd2Service {


    @Override
    public AispDTO getAisp(String microserviceKey, String requestId) {
        return new AispDTO();
    }
}
