package com.ironlady.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ironlady.chatbot")
public class IronladyAiChatbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(IronladyAiChatbotApplication.class, args);
    }
}
