package com.bignerdranch.android.sportsgbt;

import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatActivity extends AppCompatActivity {

    private TextView textViewChat;
    private EditText editTextMessage;
    private OpenAIInterface openAIInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Initialize views
        textViewChat = findViewById(R.id.textViewChat);
        editTextMessage = findViewById(R.id.editTextMessage);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openai.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the interface using Retrofit
        openAIInterface = retrofit.create(OpenAIInterface.class);

        // Get sports personality name from intent
        String sportsPersonalityName = getIntent().getStringExtra("SPORTS_PERSONALITY");

        // Connect to the corresponding bot powered by AI
        connectToBot(sportsPersonalityName);

        // Set up event listener for sending a message
        // Set up event listener for sending a message
        editTextMessage.setOnEditorActionListener((v, actionId, event) -> {
            Log.d("DAREN-DEBUG", "action: " + actionId);
            Log.d("DAREN-DEBUG", "event: " + event);
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String message = editTextMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    sendMessageToBot(message);
                    editTextMessage.setText(""); // Clear the EditText after sending message
                }
                return true;
            }
            return false;
        });

    }

// Rest of your code remains the same...


    // Method to connect to the bot powered by AI
    private void connectToBot(String sportsPersonalityName) {
        // Implement logic to connect to the corresponding bot
        // For demonstration purposes, just display the name of the sports personality
        textViewChat.setText("Chatting with " + sportsPersonalityName + " bot...");
    }

    // Method to send a message to the bot
    // Method to send a message to the bot
    private void sendMessageToBot(String message) {
        // Create an instance of AIRequest with the user's message
        AIRequest aiRequest = new AIRequest(message, "gpt-3.5-turbo");

        // Make API call to OpenAI API to get bot response
        Call<ResponseBody> call = openAIInterface.getCompletion(aiRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        // Parse the response body and update UI with bot response
                        String botResponse = response.body().string();
                        updateChatUI("User: " + message);
                        updateChatUI("Bot: " + botResponse);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Handle error
                    try {
                        String error = response.errorBody().string();
                        Toast.makeText(ChatActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle failure
                Toast.makeText(ChatActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    // Method to update the chat UI with messages
    private void updateChatUI(String message) {
        String currentChat = textViewChat.getText().toString();
        currentChat += "\n" + message;
        textViewChat.setText(currentChat);
    }
}
