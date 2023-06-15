package com.vn.assignmentjava5.schedule;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class CheckLogout {
    @Scheduled(fixedRate = 5000*10)
    public String logout(){
        return ("views/index");
    }

}
