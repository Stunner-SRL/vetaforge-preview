package ai.stunner.vetaforge.service.flowable.services;

import com.google.gson.Gson;
import ai.stunner.vetaforge.service.events.error.exceptions.ServerException;
import ai.stunner.vetaforge.service.flowable.entities.FlowableTaskDTO;
import ai.stunner.vetaforge.service.flowable.entities.FlowableVariableDTO;
import ai.stunner.vetaforge.service.flowable.entities.Order;
import ai.stunner.vetaforge.service.flowable.entities.Variables;
import ai.stunner.vetaforge.service.flowable.util.RequestBodyBuilder;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UpdateVariablesService extends TaskActionService {

    private final Logger log = LoggerFactory.getLogger(ProcessInstanceService.class);

    public UpdateVariablesService(Gson gson) {
        super(gson);
    }


    public void updateProcessVariables(String processId, Variables variables) throws Exception {
        // Must check because if only files were modified, it does not change flowable state
        if (variables.asList().size() > 0) {
            restClient.updateProcessInstanceVariables(processId, variables);
        }

//        Map<String, Object> body = new RequestBodyBuilder()
//            .setProcessInstanceId(processId)
//            .addOrder(new Order(Order.START_TIME, Order.ORDER_DESC))
//            .build();
//        List<FlowableTaskDTO> tasks = restClient
//            .queryHistoricTasks(body)
//            .getData();
//        FlowableTaskDTO lastTask =  tasks.get(tasks.size() - 1);
//
//        this.completeTask(lastTask.getId());

//        return lastTask;
    }

    private void finishUpdate(String processInstanceId) {
        // Get task
        FlowableTaskDTO waitTask = getWaitTask(processInstanceId);
        // Complete wait task
        completeTask(waitTask.getId());
    }

    private FlowableTaskDTO getWaitTask(String processInstanceId) {
        Map<String, Object> body = new RequestBodyBuilder()
            .setProcessInstanceId(processInstanceId)
            .addOrder(new Order(Order.START_TIME, Order.ORDER_DESC))
            .build();
        return restClient
            .queryHistoricTasks(body)
            .getData()
            .stream()
            .filter(t -> t.getTaskDefinitionKey().contains("wait"))
            .findFirst()
            .orElseThrow(() -> new ServerException("Something went wrong"));
    }

}
