package hu.schonherz.training.helpdesk.data.enums;

public enum ActivityType {
    BUTTON_CLICK("buttonClick"),
    NAVIGATION("navigation"),
    INPUT_FOCUSLOSS("inputFocusloss"),
    FORM_SUBMIT("formSubmit");

    private final String text;

    ActivityType(final String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
