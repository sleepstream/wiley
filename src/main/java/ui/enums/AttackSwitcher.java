package ui.enums;

public enum AttackSwitcher {

    USB_ATTACK("#attacks > .section__nav.clearfix > ul > li:nth-child(2) > a"),
    EMAIL_ATTACK("#attacks > .section__nav.clearfix > ul > li:nth-child(1) > a");

    public String selector;

    AttackSwitcher(String selector) {
        this.selector = selector;
    }

}
