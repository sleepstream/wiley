package ui.enums;

public enum EmployeeData {

    NEW_EMPLOYEE_MODAL_HEADER("Новый сотрудник", "New employee"),
    IMPORT_FROM_FILE_MODAL_HEADER("Новый сотрудник", "Import from File"),
    IMPORT_FROM_AD_MODAL_HEADER("Новый сотрудник", "Import from Active Directory");

    public String ru;
    public String en;

    EmployeeData(String ru, String en) {
        this.ru = ru;
        this.en = en;
    }
}
