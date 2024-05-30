package ai.stunner.vetaforge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import ai.stunner.vetaforge.config.ApplicationProperties;
import ai.stunner.vetaforge.domain.*;
import ai.stunner.vetaforge.service.dto.aisp.AispDTO;
import ai.stunner.vetaforge.service.dto.core.AdherentDTO;
import ai.stunner.vetaforge.service.feign.CoreService;
import ai.stunner.vetaforge.service.feign.Psd2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LocalDataCollector {

    private final Logger log = LoggerFactory.getLogger(LocalDataCollector.class);

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    CollectionContainerService collectionContainerService;

    @Autowired
    Gson gson;

    @Autowired
    Psd2Service psd2Service;

    @Autowired
    CoreService coreService;

    public CollectionContainer createSurveyDataCollector(FactoringRequest factoringRequest, AdherentDTO adherent) throws IllegalAccessException, JsonProcessingException {
        log.info("Create survey data collector");
        // add collection
        CollectionContainer collectionContainer = new CollectionContainer();
        adherent.setDebtors(factoringRequest.getDebtors());
        adherent.getDebtors().forEach(debtor -> {
            String debtorDetails = coreService.getCompanyDetails(debtor.getTaxCode(), true, factoringRequest.getId()).get("details").toString();
            debtor.setCompanyDetailsJSON(debtorDetails);
        });
        String jsonData = gson.toJson(adherent);
        collectionContainer.setFactoringRequest(factoringRequest);
        collectionContainer.setType("ADHERENT_SURVEY");
        Map<String, Object> result = new ObjectMapper().readValue(jsonData, HashMap.class);
        collectionContainer.setValues(result);

        return collectionContainerService.save(collectionContainer);
    }

    public CollectionContainer addAISPCollection(String ownerId, FactoringRequest factoringRequest) {
        CollectionContainer collectionContainer = new CollectionContainer();
        collectionContainer.setFactoringRequest(factoringRequest);
        collectionContainer.setType("AISP_COLLECTION");
        try {
            AispDTO aisp = psd2Service.getAisp(applicationProperties.getApiKeys().get("microserviceKey"), ownerId);
            HashMap<String, Object> values = new HashMap<>();
            values.put("banks", aisp.getBanks());
            collectionContainer.setValues(values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.debug("ADD AISP_COLLECTION------------------------------------\n\n\n");
        log.debug(collectionContainer.getValues().toString());

        return collectionContainerService.save(collectionContainer);
    }
}
