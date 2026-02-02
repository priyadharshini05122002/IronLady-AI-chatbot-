package com.ironlady.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ironlady.chatbot.service.ChatService;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    // Load chat UI
    @GetMapping("/chat")
    public String showChatPage() {
        return "chat"; // templates/chat.html
    }

    // Handle chat message (AJAX)
    @PostMapping("/chat")
    @ResponseBody
    public String chat(@RequestParam("message") String message) {
        System.out.println("USER MESSAGE => " + message); // DEBUG
        return chatService.getResponse(message);
    }
}
