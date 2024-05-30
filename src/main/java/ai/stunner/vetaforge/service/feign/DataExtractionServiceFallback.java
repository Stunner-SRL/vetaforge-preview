package ai.stunner.vetaforge.service.feign;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class DataExtractionServiceFallback implements DataExtractionService { //restart

    @Override
    public String extractData(MultipartFile file, String templateName) {
        return null;
    }
}
