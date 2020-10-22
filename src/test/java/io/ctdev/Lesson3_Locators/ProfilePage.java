package io.ctdev.Lesson3_Locators;

public class ProfilePage {
    private String nameCss="[name=name]";
    private String nameXpath="//input[@name='name']";
    private String loginCss="[name=login]";
    private String loginXpath="//input[@name='login']";
    private String phoneNumberCss="[name=phoneNumber]";
    private String phoneNumberXpath="//input[@name='phoneNumber']";
    private String positionCss="[name=position]";
    private String positionXpath="//input[@name='position']";
    private String departmentCss="[name=department]";
    private String departmentXpath="//input[@name='department']";
    private String cancelCss="[.button_btn__2GBfK.editUserProfile_cancelBtn__fnKLZ]";
    private String cancelXpath="//button[@type='button' and contains(., 'Cancel')]";
    private String cancelButtonCss="[.button_btn__2GBfK.editUserProfile_cancelBtn__fnKLZ]";
    private String cancelButtonXpath="//button[@type='button' and contains(., 'Cancel')]";
    private String saveChangesCss="[type=submit]";
    private String saveChangesXpath="//button[@type='submit']";
}
