package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";
    @Autowired
    private SimpleMailService simpleEmailService;
    @Autowired
    private  TaskRepository taskRepository;
    @Autowired
    private  AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String message = "";
        Mail mail = new Mail(adminConfig.getAdminMail(), SUBJECT, message, null);
        if (size > 1) {
            mail.setMessage("Currently in database you got " + size + " tasks");
        } else {
            mail.setMessage("Currently in database you got " + size + " task");
        }
        simpleEmailService.send(mail);
    }
}