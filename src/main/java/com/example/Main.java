package com.example;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class Main {
    public static void main(String[] args) {
       // System.out.println ("Key : " + System.getenv("OPENAI_API_KEY"));
        OpenAIClient client = OpenAIOkHttpClient.fromEnv();

       ResponseCreateParams params = ResponseCreateParams.builder()
                .input("what is time in India now ")
                .model("gpt-4.1-mini")
                .build();

        Response response = client.responses().create(params);
        System.out.println(response.output());
    }
}