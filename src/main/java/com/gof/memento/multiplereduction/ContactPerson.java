package com.gof.memento.multiplereduction;

/**
 * 备忘录模式  联系人
 */
public class ContactPerson {
    public String name;
    public String mobileNum;

    public ContactPerson(String name, String mobileNum) {
        this.name = name;
        this.mobileNum = mobileNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", mobileNum='" + mobileNum + '\'';
    }
}
