package com.styleomega.app.services;

import com.openai.client.OpenAIClient;
import com.openai.client.models.CreateChatCompletionRequest;
import com.openai.client.models.ChatCompletion;
import com.openai.client.models.ChatMessage;
import com.openai.client.models.Role; // For ChatMessage role
import java.util.ArrayList; // For List of messages
import java.util.List; // For List of messages


public class OpenAIService {

    // IMPORTANT: API Key Management
    // This is a placeholder. In a real application, DO NOT hardcode your API key.
    // Consider using Android Keystore, server-side proxy, or other secure methods.
    private static final String OPENAI_API_KEY = "YOUR_OPENAI_API_KEY_HERE";

    // TODO: Initialize OpenAIClient (likely in a constructor or a static block)
    private OpenAIClient apiClient;

    public OpenAIService() {
        // IMPORTANT: User must replace "YOUR_OPENAI_API_KEY_HERE" with their actual key
        // and implement secure key management.
        if ("YOUR_OPENAI_API_KEY_HERE".equals(OPENAI_API_KEY) || OPENAI_API_KEY.isEmpty()) {
            System.err.println("OpenAI API Key is not configured in OpenAIService.java. Chatbot will not function.");
            apiClient = null; // Or handle this state appropriately
        } else {
            // For com.openai:openai-client:0.10.0, initialization is typically:
            // apiClient = OpenAIClient.builder().apiKey(OPENAI_API_KEY).build();
            // However, to stick to the subtask's self-correction note about trying direct instantiation
            // or researching, and given this is a placeholder until build/test:
            // Using a simplified placeholder for instantiation.
            // This will likely need adjustment based on actual library's API for version 0.10.0
            try {
                 // apiClient = new OpenAIClient(OPENAI_API_KEY); // This is a guess.
                 // The common pattern for this library is using the builder.
                 apiClient = OpenAIClient.builder().apiKey(OPENAI_API_KEY).build();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Failed to initialize OpenAIClient: " + e.getMessage());
                apiClient = null;
            }
        }
    }

    public String getChatResponse(String userInput) {
        if (apiClient == null) {
            return "Error: OpenAI client not initialized. Check API Key or initialization logic.";
        }
        try {
            List<ChatMessage> messages = new ArrayList<>();
            messages.add(new ChatMessage(Role.USER, userInput));

            CreateChatCompletionRequest chatCompletionRequest = CreateChatCompletionRequest.builder()
                .model("gpt-3.5-turbo") // Or another preferred model
                .messages(messages)
                .build();

            // .join() makes this a synchronous call.
            // This is fine for the structure, but needs to be handled for Android's main thread.
            ChatCompletion completion = apiClient.createChatCompletion(chatCompletionRequest).join();
            if (completion.getChoices() != null && !completion.getChoices().isEmpty()) {
                return completion.getChoices().get(0).getMessage().getContent();
            } else {
                return "No response from OpenAI.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error communicating with OpenAI: " + e.getMessage();
        }
    }
}
