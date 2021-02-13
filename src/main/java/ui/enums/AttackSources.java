package ui.enums;

import static org.testng.Assert.fail;

public enum AttackSources {

    EXTERNAL("Внешняя атака", "External attack", 2),
    CORPORATE("Корпоративная", "Corporate", 3),
    PRIVATE("Личная", "Private", 4);

    public String ru;
    public String en;
    public Integer id;

    AttackSources(String ru, String en, Integer id) {
        this.ru = ru;
        this.en = en;
        this.id = id;
    }

    public static AttackSources getAttackSourceById(Integer id) {
        for (AttackSources attackSources : AttackSources.values()) {
            if (attackSources.id.equals(id)) {
                return attackSources;
            }
        }
        //fail if source id not found
        fail("Unrecognized source id " + id);

        return null;
    }
}
