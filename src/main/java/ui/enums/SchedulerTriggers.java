package ui.enums;

public enum SchedulerTriggers {

    EMPLOYEE_ADDED_TO_PLATFORM("Сотрудник добавлен в систему", "Employee added to platform", 0),
    NEW_EMPLOYEE("Новый сотрудник", "New employee", 1),
    EMPLOYEE_IS_ACTIVE("Сотрудник активен", "Employee is active...", 2),
    EMPLOYEE_IS_NOT_CHECKED("Сотрудник не проверялся ...", "Employee is not checked ...", 3),
    ATTACK_ASSIGNED_TO_EMPLOYEE("На сотрудника назначена атака", "Attack assigned to employee", 4),
    OPENED_EMAIL("Открыл письмо", "Opened email", 5),
    FOLLOWED_THE_LINK("Перешёл по ссылке", "Followed the link", 6),
    OPENED_ATTACHMENT("Открыл вложение", "Opened attachment",  7),
    ENTER_DATA_INTO_THE_FORM("Ввёл данные в форму", "Entered data into the form", 8),
    VULNERABILITIES_FOUND("Обнаружены уязвимости", "Vulnerabilities found", 9),
    WITHSTOOD_THE_ATTACK("Выдержал атаку ...", "Withstood the attack...", 10),
    EMPLOYEE_MISSED_TRAINED("Сотрудник не был обучен ...", "Employee missed training ...", 11),
    ASSIGNED_TO_TRAINING("Назначен на обучение ...", "Assigned to training ...", 12),
    COMPLETED_TRAINING("Завершил обучение ...", "Completed training ...", 13),
    FAILED_TO_PASS_TRAINING_ON_TIME("Не прошел обучение вовремя ...", "Failed to pass training on time ...", 14),
    EMPLOYEE_RANK("Рейтинг сотрудника ...", "Employee rank ...", 15),
    RANK_IN_THE_LAST_ATTACK("Рейтинг в последней атаке ...", "Rank in the last attack ...", 16);

    public String ru;
    public String en;
    public Integer id;

    SchedulerTriggers(String ru, String en, Integer id) {
        this.ru = ru;
        this.en = en;
        this.id = id;
    }
}
