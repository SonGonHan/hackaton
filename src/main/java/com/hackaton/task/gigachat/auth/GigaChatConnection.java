package com.hackaton.task.gigachat.auth;

import chat.giga.client.GigaChatClient;
import chat.giga.client.auth.AccessToken;
import chat.giga.client.auth.AuthClient;
import chat.giga.client.auth.AuthClientBuilder;
import chat.giga.model.ModelName;
import chat.giga.model.Scope;
import chat.giga.model.completion.ChatMessage;
import chat.giga.model.completion.CompletionRequest;
import org.springframework.beans.factory.annotation.Value;

public class GigaChatConnection {

   @Value("app.giga-chat.auth-token")
   private static String authKey;

   public static AccessToken getToken() {
      AuthClient client = AuthClient.builder()
            .withOAuth(
                  AuthClientBuilder.OAuthBuilder.builder()
                        .scope(Scope.GIGACHAT_API_PERS)
                        .authKey(authKey)
                        .build()).build();

      return client.getToken();
   }

   public static String sendRequest(String message) {
      AccessToken token = getToken();

      GigaChatClient client = GigaChatClient.builder()
            .authClient(AuthClient.builder()
                  .withOAuth(AuthClientBuilder.OAuthBuilder.builder()
                        .scope(Scope.GIGACHAT_API_PERS)
                        .authKey(token.toString())
                        .build())
                  .build())
            .build();

      String response = client.completions(CompletionRequest.builder()
            .model(ModelName.GIGA_CHAT)
            .message(ChatMessage.builder()
                  .content(message)
                  .role(ChatMessage.Role.USER)
                  .build())
            .build())
            .toString();

      return response;
   }

}
