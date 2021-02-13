package utils;

import java.util.Random;

public class GenerateTestData {

    //todo create methods to generate data with html special chars
    public static String generateRandomLatinText(int length) {
        String regex = "[a-zA-Z]";
        return generate(regex, length);
    }

    public static String generateRandomCyrillicText(int length) {
        String regex = "[а-яА-Я]";
        return generate(regex, length);
    }

    public static String generateRandomText(int length) {
        String regex = "[a-zA-Zа-яА-Я]";
        return generate(regex, length);
    }

    public static String generateRandomLatinTextWithCharsNumbers(int length) {
        String regex = "[a-zA-Z0-9]";
        return generate(regex, length);
    }

    public static String generateRandomTextWithCharsNumbersSymbols(int length) {
        String regex = "[a-zA-Zа-яА-Я0-9~`!@#$%^*()_=;':,./?-]";
        return generate(regex, length);
    }

    public static String generateRandomTextWithCharsNumbersLimitSymbols(int length) {
        String regex = "[a-zA-Zа-яА-Я0-9!()_?-]";
        return generate(regex, length);
    }

    public static String generateRandomLatinTextWithCharsNumbersSymbols(int length) {
        String regex = "[a-zA-Z0-9~`!@#$%^*()_=;':,./?-]";
        return generate(regex, length);
    }

    public static String generateRandomEmail(int length) {
        String regex = "[a-zA-Z0-9]";
        return ("AutoTest_.-" + generate(regex, length)).toLowerCase();
    }

    public static String generateRandomPhoneNumber(int length) {
        String regex = "[0-9]";
        String result = "+7" + generate(regex, length);
        return result;
    }

    public static String generateRandomNumber(int length) {
        String regex = "[0-9]";
        String result = generate(regex, length);
        return result;
    }

    public static String generateRandomDomain(int length, int level) {
        String regex = "[a-z0-9-]";
        String result = "autotest-" + generate("[a-z0-9]", 1);
        Random random = new Random();
        for(int i = 0; i<=level; i++) {
            result += generate("[a-z0-9]", 1);
            result += generate(regex, random.nextInt(length));
            result += generate("[a-z0-9]", 1);
            result += ".";
        }
        result += generate("[a-z]", 2);
        return result;
    }

    private static String generate(String regex, Integer length) {
        String result = "";
        while (length >= 0) {
            String regexWithLength = regex;
            int f1 = length % 1000;
            int f2  = length - (int) (length / 1000) * 1000;
            if ((int)(length / 1000) == 0) {
                regexWithLength += "{" + length + "}";
            }
            else {
                regexWithLength += "{1000}";
            }
            length -= 1000;
            Xeger generator = new Xeger(regexWithLength);
            result += generator.generate();
        }
        return result;
    }

}
