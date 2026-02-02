package com.ironlady.chatbot.service;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public String getResponse(String message) {

        // ✅ IMAGE MESSAGE HANDLING (NEW)
        if (message != null && message.equalsIgnoreCase("__IMAGE__")) {
            return "📸 I see you've shared an image!\n\n"
                 + "You can explore Iron Lady programs, enrollment, and success stories here:\n\n"
                 + "🔗 https://www.iamironlady.com\n\n"
                 + "Would you like details about programs, fees, or enrollment?";
        }

        // Empty message
        if (message == null || message.trim().isEmpty()) {
            return "Please type something! I'm here to help you learn about Iron Lady programs 😊";
        }

        String lowerMessage = message.toLowerCase().trim();

        // Greetings
        if (isGreeting(lowerMessage)) {
            return "Hello! 👋 Welcome to Iron Lady – India’s premier leadership platform for women.\n\n"
                 + "I can help you with:\n"
                 + "🎯 Programs (LEP, 100 Board Members, 1 Crore Club, Masterclass)\n"
                 + "💰 Fees & scholarships\n"
                 + "📝 Enrollment process\n"
                 + "⭐ Success stories\n\n"
                 + "What would you like to know?";
        }

        // Farewell
        if (isFarewell(lowerMessage)) {
            return "Thank you for chatting 💜\n\n"
                 + "Visit our official website to continue your journey:\n"
                 + "🔗 https://www.iamironlady.com\n\n"
                 + "Stay empowered 💪";
        }

        // Enrollment
        if (lowerMessage.contains("enroll") || lowerMessage.contains("join")
                || lowerMessage.contains("register") || lowerMessage.contains("sign up")) {
            return "📝 HOW TO ENROLL\n\n"
                 + "1️⃣ Start with the Masterclass (₹99–129)\n"
                 + "2️⃣ Choose your program (LEP / 100 Board Members / 1 Crore Club)\n"
                 + "3️⃣ Register on our website\n\n"
                 + "🔗 https://www.iamironlady.com\n\n"
                 + "Need help choosing a program?";
        }

        // Fees
        if (lowerMessage.contains("fee") || lowerMessage.contains("cost")
                || lowerMessage.contains("price")) {
            return "💰 PROGRAM FEES\n\n"
                 + "• Masterclass: ₹99–129 (Money-back guarantee)\n"
                 + "• Main programs: Vary by program\n"
                 + "• Scholarships & EMI options available\n\n"
                 + "📧 admin@iamironlady.com\n"
                 + "🌐 https://www.iamironlady.com";
        }

        // Programs
        if (lowerMessage.contains("program")) {
            return "📚 IRON LADY PROGRAMS\n\n"
                 + "1️⃣ Masterclass – Best starting point\n"
                 + "2️⃣ Leadership Essentials Program (LEP)\n"
                 + "3️⃣ 100 Board Members Program\n"
                 + "4️⃣ 1 Crore Club\n\n"
                 + "Which one would you like to explore?";
        }

        // Success stories
        if (lowerMessage.contains("success") || lowerMessage.contains("story")) {
            return "⭐ SUCCESS STORIES\n\n"
                 + "• Women closing 30L+ deals\n"
                 + "• 1CR+ income achievers\n"
                 + "• UN ambassadors & board leaders\n\n"
                 + "Read more inspiring stories here:\n"
                 + "🔗 https://www.iamironlady.com";
        }

        // Contact
        if (lowerMessage.contains("contact") || lowerMessage.contains("email")) {
            return "📞 CONTACT US\n\n"
                 + "📧 admin@iamironlady.com\n"
                 + "🌐 https://www.iamironlady.com\n\n"
                 + "We usually respond within 24–48 hours.";
        }

        // Thank you
        if (lowerMessage.contains("thank")) {
            return "You're very welcome 😊\n\n"
                 + "Anything else you'd like to know about Iron Lady?";
        }

        // Default
        return "I’m here to help you with Iron Lady programs 😊\n\n"
             + "You can ask:\n"
             + "• Programs offered\n"
             + "• Fees & scholarships\n"
             + "• Enrollment process\n"
             + "• Success stories\n\n"
             + "🌐 https://www.iamironlady.com";
    }

    private boolean isGreeting(String message) {
        String[] greetings = {
                "hello", "hi", "hey", "good morning",
                "good afternoon", "good evening", "namaste"
        };
        for (String g : greetings) {
            if (message.startsWith(g)) return true;
        }
        return false;
    }

    private boolean isFarewell(String message) {
        return message.contains("bye")
                || message.contains("goodbye")
                || message.contains("see you")
                || message.contains("take care");
    }
}
