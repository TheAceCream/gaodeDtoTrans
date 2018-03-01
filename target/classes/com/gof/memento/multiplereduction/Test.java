package com.gof.memento.multiplereduction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 备忘录模式 多还原点保存
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        List<ContactPerson> persons = new ArrayList<ContactPerson>();
        persons.add(new ContactPerson("Learning Hard","123445"));
        persons.add(new ContactPerson("Tony","234565"));
        persons.add(new ContactPerson("Jock","231455"));


        MobileOwner mobileOwner = new MobileOwner(persons);
        mobileOwner.Show();

        // 创建备忘录并保存备忘录对象
        Caretaker caretaker = new Caretaker();
        caretaker.contactMementoDic.put("1",mobileOwner.createMemento());

        // 更改发起人联系人列表
        System.out.println("----移除最后一个联系人--------");
        mobileOwner.contactPersons.remove(2);
        mobileOwner.Show();

        // 创建第二个备份
        Thread.sleep(1000);
        caretaker.contactMementoDic.put("2",mobileOwner.createMemento());

        // 恢复到原始状态
        System.out.println("-------备忘录列表------");
        Set<String> keys = caretaker.contactMementoDic.keySet();
        for(String s:keys){
            System.out.println(s);
            ContactMemento contactMemento = (ContactMemento)caretaker.contactMementoDic.get(s);
            List<ContactPerson> pers = contactMemento.getContactPersonBack();
            for(ContactPerson contactPerson:pers){
                System.out.println(contactPerson.toString());
            }
        }

    }
}
