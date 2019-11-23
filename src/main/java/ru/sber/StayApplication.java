package ru.sber;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import java.util.HashMap;

@SpringBootApplication
@EnableProcessApplication
public class StayApplication {

    public static void main(String[] args) {
        SpringApplication.run(StayApplication.class, args);
    }

    @Autowired
    private RuntimeService runtimeService;

    @EventListener
    private void processPostDeploy(PostDeployEvent event) {
        HashMap<String, Object> vars = new HashMap<>();
        vars.put("season", "Fall");
        vars.put("guestCount", Integer.valueOf(3));
        runtimeService.startProcessInstanceByKey("loanApproval", vars);
    }

}
