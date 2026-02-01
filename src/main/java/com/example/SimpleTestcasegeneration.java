package com.example;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class SimpleTestcasegeneration {

    public static void main(String[] args) {
        String apikey = Config.getApikey();
        OpenAIClient client = OpenAIOkHttpClient.builder().apiKey(apikey).build();
                
        String userstory = "As a register user, I want to create an account so that I can access member-only features.Also I should be able to reset my password so that i can regain access if i forgot the password."
                + "Also create test cases for seccurity checks for this login page";

        String prompt = "You are a Software QA engineer , given the following user story , generate 3 testcases in Gherkin format (Given /when /then).\n"
                +
                "Provide each test case with :\n" +
                "- Title\n" +
                "-Preconditions\n" +
                "- Steps in Gherkin format\n" +
                "- Expected Results\n" +
                "User Story : " + userstory +
                "\n\n Respond only with JSON array of objects with fields , preconditions , Gherkin , expected.\n";

        ResponseCreateParams params = ResponseCreateParams.builder()
                .input(prompt).model("gpt-4.1-mini").build();

        try {
            Response response = client.responses().create(params);
            System.out.println("Raw response object :" + response.output());
        } catch (Exception e) {
            System.err.println("Error calling open AI :" + e.getMessage());
            e.printStackTrace();
        }   
    }
}