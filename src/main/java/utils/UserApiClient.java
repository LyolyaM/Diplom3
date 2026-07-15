package utils;

import models.User;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserApiClient {

    private static final HttpClient client = HttpClient.newHttpClient();

    // Быстрая регистрация пользователя перед тестом (возвращает accessToken)
    public static String registerUser(User user) {
        try {
            String jsonBody = String.format(
                    "{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}",
                    user.getEmail(), user.getPassword(), user.getName()
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(AppConfig.BASE_URL + "api/auth/register"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String body = response.body();
                int tokenIndex = body.indexOf("accessToken\":\"");
                if (tokenIndex != -1) {
                    int start = tokenIndex + 14;
                    int end = body.indexOf("\"", start);
                    return body.substring(start, end);
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка при регистрации пользователя через API: " + e.getMessage());
        }
        return null;
    }

    // Удаление пользователя после теста по его accessToken
    public static void deleteUser(String token) {
        if (token == null) return;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(AppConfig.BASE_URL + "api/auth/user"))
                    .header("Authorization", token)
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 202) {
                System.out.println(" Тестовый пользователь успешно удален из базы через API.");
            } else {
                System.err.println(" Не удалось удалить пользователя. Код ответа: " + response.statusCode());
            }
        } catch (Exception e) {
            System.err.println("Ошибка при удалении пользователя через API: " + e.getMessage());
        }
    }
}
