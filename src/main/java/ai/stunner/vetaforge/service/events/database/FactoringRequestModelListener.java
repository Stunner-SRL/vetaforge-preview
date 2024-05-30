package ai.stunner.vetaforge.service.events.database;

import ai.stunner.vetaforge.domain.FactoringRequest;
import ai.stunner.vetaforge.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;



@Component
public class FactoringRequestModelListener extends AbstractMongoEventListener<FactoringRequest> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public FactoringRequestModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<FactoringRequest> event) {
        if (event.getSource().getSeqId() < 1) {
            event.getSource().setSeqId(sequenceGenerator.generateSequence(FactoringRequest.SEQUENCE_NAME));
        }
    }


}
