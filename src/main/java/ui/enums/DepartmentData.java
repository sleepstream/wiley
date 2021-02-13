package ui.enums;

public enum DepartmentData {

    MODAL_TITLE("Новый отдел", "New department"),
    LABEL_FOR_NAME("Название", "Name"),
    LABEL_FOR_DEPARTMENT("Внутри отдела", "Within the department");

    public String ru;
    public String en;

    DepartmentData(String ru, String en) {
        this.ru = ru;
        this.en = en;
    }

}
