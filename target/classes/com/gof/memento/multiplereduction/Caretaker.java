package com.gof.memento.multiplereduction;

import java.util.HashMap;
import java.util.Map;

/**
 * 备忘录模式 管理角色
 */
public class Caretaker {
    // 使用多个备忘录来存储多个备份点
    public Map<String, ContactMemento> contactMementoDic;

    public Caretaker()
    {
        contactMementoDic = new HashMap<String, ContactMemento>();
    }

    public Map<String, ContactMemento> getContactMementoDic() {
        return contactMementoDic;
    }

    public void setContactMementoDic(Map<String, ContactMemento> contactMementoDic) {
        this.contactMementoDic = contactMementoDic;
    }
}
