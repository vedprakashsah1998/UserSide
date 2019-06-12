package com.client.vpman.userside;

import com.google.firebase.database.Exclude;

public class TournamentDetail
{
    private String NameHolder1, DescHold1,DateHold1,TimeHold1,MoneyHold1,image,mKey;

    public TournamentDetail() {
    }

    public TournamentDetail(String nameHolder1, String descHold1, String dateHold1, String timeHold1, String moneyHold1, String image) {
        NameHolder1 = nameHolder1;
        DescHold1 = descHold1;
        DateHold1 = dateHold1;
        TimeHold1 = timeHold1;
        MoneyHold1 = moneyHold1;
        this.image = image;
    }

    public String getNameHolder1() {
        return NameHolder1;
    }

    public void setNameHolder1(String nameHolder1) {
        NameHolder1 = nameHolder1;
    }

    public String getDescHold1() {
        return DescHold1;
    }

    public void setDescHold1(String descHold1) {
        DescHold1 = descHold1;
    }

    public String getDateHold1() {
        return DateHold1;
    }

    public void setDateHold1(String dateHold1) {
        DateHold1 = dateHold1;
    }

    public String getTimeHold1() {
        return TimeHold1;
    }

    public void setTimeHold1(String timeHold1) {
        TimeHold1 = timeHold1;
    }

    public String getMoneyHold1() {
        return MoneyHold1;
    }

    public void setMoneyHold1(String moneyHold1) {
        MoneyHold1 = moneyHold1;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Exclude
    public String getmKey() {
        return mKey;
    }

    @Exclude
    public void setmKey(String mKey) {
        this.mKey = mKey;
    }
}
