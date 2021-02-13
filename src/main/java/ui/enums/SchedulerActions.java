package ui.enums;

public enum SchedulerActions {

    SEND_TO_TRAINING("Отправить на обучение", "Send to training", 0),
    LAUNCH_ATTACK("Запустить атаку", "Launch attack", 1),
    GENERATE_AND_SEND_A_REPORT("Сформировать и отправить отчёт", "Generate and send a report", 2);

    public String ru;
    public String en;
    public Integer id;

    SchedulerActions(String ru, String en, Integer id) {
        this.ru = ru;
        this.en = en;
        this.id = id;
    }
}
