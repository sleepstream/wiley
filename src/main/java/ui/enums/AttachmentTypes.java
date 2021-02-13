package ui.enums;

import static org.testng.Assert.fail;

public enum AttachmentTypes {

    EXCEL("ext-excel", "xlsx"),
    HTML("ext-html", "html"),
    TEXT("ext-text", "js"),
    WORD("ext-word", "docx"),
    PDF("ext-pdf", "pdf"),
    RTF("ext-richtext", "rtf"),
    PPTX("ext-powerpoint", "pptx");

    //todo add selectors as enum attribute

    public String className;
    public String typeName;

    AttachmentTypes(String className, String typeName) {
        this.className = className;
        this.typeName = typeName;
    }

    public static AttachmentTypes getAttachmentByType(String type) {
        for (AttachmentTypes attachmentTypes : AttachmentTypes.values()) {
            if (attachmentTypes.typeName.equals(type)) {
                return attachmentTypes;
            }
        }
        //fail if attachment type not found
        fail("Unrecognized attachment type " + type);

        return null;
    }
}
