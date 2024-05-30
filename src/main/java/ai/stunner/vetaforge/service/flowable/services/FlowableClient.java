package ai.stunner.vetaforge.service.flowable.services;

import com.google.gson.Gson;
import ai.stunner.vetaforge.service.flowable.client.FlowableRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlowableClient {

    private final Logger log = LoggerFactory.getLogger(FlowableClient.class);

    protected final Gson gson;

    @Autowired
    protected FlowableRestClient restClient;

    public FlowableClient(Gson gson) {
        this.gson = gson;
    }
}
