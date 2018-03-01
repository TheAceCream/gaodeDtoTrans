package com.gof.memento.multiplereduction;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录模式 发起人
 */
public class MobileOwner {

    public List<ContactPerson> contactPersons;

    public MobileOwner(List<ContactPerson> persons)
    {
        contactPersons = persons;
    }

    // 创建备忘录，将当期要保存的联系人列表导入到备忘录中
    public ContactMemento createMemento()
    {
        return new ContactMemento(new ArrayList<ContactPerson>(this.contactPersons));
    }

    // 将备忘录中的数据备份导入到联系人列表中
    public void restoreMemento(ContactMemento memento)
    {
        if (memento != null)
        {
            this.contactPersons = memento.contactPersonBack;
        }
    }

    public void Show()
    {
        System.out.println("联系人列表中有"+contactPersons.size()+"个人，他们是:");
        for (ContactPerson p : contactPersons)
        {
            System.out.println("姓名: "+p.getName()+" 号码为: "+p.getMobileNum());
        }
    }
}
