package com.gof.memento.multiplereduction;

import java.util.List;

/**
 * 备忘录模式 备忘录
 */
public class ContactMemento {
    public List<ContactPerson> contactPersonBack;

    public ContactMemento(List<ContactPerson> persons)
    {
        contactPersonBack = persons;
    }

    public List<ContactPerson> getContactPersonBack() {
        return contactPersonBack;
    }

    public void setContactPersonBack(List<ContactPerson> contactPersonBack) {
        this.contactPersonBack = contactPersonBack;
    }
}
