package com.styleomega.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.styleomega.app.services.OpenAIService; // Import OpenAIService
import java.util.ArrayList;
import java.util.List;

public class ChatbotActivity extends AppCompatActivity {

    private RecyclerView recyclerViewChat;
    private EditText editTextUserInput;
    private Button buttonSendMessage;
    private ChatMessageAdapter chatAdapter;
    private List<ChatMessageData> messageList = new ArrayList<>();
    private OpenAIService openAIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot); // Set content view

        // Initialize UI elements
        recyclerViewChat = findViewById(R.id.recyclerViewChat);
        editTextUserInput = findViewById(R.id.editTextUserInput);
        buttonSendMessage = findViewById(R.id.buttonSendMessage);

        // Initialize OpenAIService
        openAIService = new OpenAIService();

        // Setup RecyclerView
        chatAdapter = new ChatMessageAdapter(messageList);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewChat.setAdapter(chatAdapter);

        // Set OnClickListener for buttonSendMessage
        buttonSendMessage.setOnClickListener(v -> {
            String userInput = editTextUserInput.getText().toString().trim();
            if (!userInput.isEmpty()) {
                addMessageToList(userInput, true);
                editTextUserInput.setText("");

                // IMPORTANT: Network calls should be on a background thread!
                // This is a simplified example. Use AsyncTask, Coroutines, or other threading mechanisms.
                // For now, this will likely cause a NetworkOnMainThreadException if API call is synchronous.
                // The OpenAIService.getChatResponse itself is synchronous due to .join().
                new Thread(() -> {
                    final String botResponse = openAIService.getChatResponse(userInput);
                    runOnUiThread(() -> addMessageToList(botResponse, false));
                }).start();
            }
        });
    }

    private void addMessageToList(String message, boolean isUser) {
        messageList.add(new ChatMessageData(message, isUser));
        chatAdapter.notifyItemInserted(messageList.size() - 1);
        recyclerViewChat.scrollToPosition(messageList.size() - 1);
    }
}
