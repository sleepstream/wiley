package ui.enums;

public enum LoginPageData {
    HEADER("Система обучения и контроля защищенности сотрудников", "Security awareness and employee behavior " +
            "management platform"),
    LABEL_FOR_HEADER("Вход для клиентов", "Client login"),
    LABEL_FOR_LOGIN("Логин", "Login"),
    LABEL_FOR_PASSWORD("Пароль", "Password"),
    MESSAGE_FOR_LOGIN_ALERT("Логин или пароль введены неверно", "Login or password entered incorrectly"),
    MESSAGE_FOR_LOGIN_NEED_UPDATE("Вы использовали разовый пароль. Необходимо задать новый", "You used a one-time password. You must set a new password"),
    MESSAGE_FOR_NOT_VALID_PASSWORD("В вашем пароле должно быть не меньше #length символов, включая: заглавные и строчные буквы, #digit цифр", "");

    public String ru;
    public String en;

    LoginPageData(String ru, String en) {
        this.ru = ru;
        this.en = en;
    }
}
