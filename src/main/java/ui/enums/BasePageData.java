package ui.enums;

public enum BasePageData {

    ANTIPHISHING_TITLE("Антифишинг", "Antiphish"),
    LEFT_FOOTER("© 2020 Антифишинг", "© 2020 Antiphish"),
    RIGHT_FOOTER("Поддержка: +7 (499) 677-19-07", "Support: +7 (499) 677-19-07");

    public String ru;
    public String en;

    BasePageData(String ru, String en) {
        this.ru = ru;
        this.en = en;
    }
}
