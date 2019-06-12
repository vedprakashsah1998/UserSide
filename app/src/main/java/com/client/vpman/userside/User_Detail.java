package com.client.vpman.userside;

public class User_Detail
{
    private String Name,PubgUserName,EmailId,MobileNo;

    public User_Detail() {
    }

    public User_Detail(String name, String pubgUserName, String emailId, String mobileNo) {
        Name = name;
        PubgUserName = pubgUserName;
        EmailId = emailId;
        MobileNo = mobileNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPubgUserName() {
        return PubgUserName;
    }

    public void setPubgUserName(String pubgUserName) {
        PubgUserName = pubgUserName;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }
}
