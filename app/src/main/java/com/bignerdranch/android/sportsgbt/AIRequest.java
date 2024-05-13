package com.bignerdranch.android.sportsgbt;

public class AIRequest {
    private String model;
    private Message[] messages;
    private Double temperature;

    public class Message {
        private String role;
        private String content;

        public Message(String content) {
            this.content = content;
            this.role = "user";
        }
    }

    public AIRequest(String prompt, String modelId) {
        this.messages = new Message[1];
        messages[0] = new Message(prompt);
        this.model = modelId;
        this.temperature = 0.7;
    }

    public String getModelId() {
        return model;
    }

    public void setModelId(String modelId) {
        this.model = modelId;
    }
}
