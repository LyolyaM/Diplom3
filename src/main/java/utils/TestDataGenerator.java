package utils;

import com.github.javafaker.Faker;
import models.User;
import java.util.Locale;

public class TestDataGenerator {
    // Faker с русской локалью для реалистичных имён
    private static final Faker faker = new Faker(new Locale("ru"));

    // Генерация имени
    public static String generateRandomName() {
        return faker.name().fullName();
    }

    // Генерация Email
    public static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    // Генерация валидного пароля (6 символов)
    public static String generateValidPassword() {
        return faker.number().digits(6);
    }

    // Генерация короткого пароля (3-5 символов) - для теста ошибки
    public static String generateShortPassword() {
        return faker.internet().password(3, 5, false, false);
    }

    // Генерация полного пользователя с валидными данными
    public static User generateValidUser() {
        return new User(
                generateRandomName(),
                generateRandomEmail(),
                generateValidPassword()
        );
    }

    // Генерация пользователя с коротким паролем
    public static User generateUserWithShortPassword() {
        return new User(
                generateRandomName(),
                generateRandomEmail(),
                generateShortPassword()
        );
    }

}
