package hu.schonherz.training.helpdesk.service.api.vo;

public enum ActivityTypeVO {
    BUTTON_CLICK("buttonClick"),
    NAVIGATION("navigation"),
    INPUT_FOCUSLOSS("inputFocusloss"),
    FORM_SUBMIT("formSubmit");

    private final String text;

    ActivityTypeVO(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
