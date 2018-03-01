package com.gof.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * 简历类
 */
public class Resume implements Cloneable{
    public String name = null;
    public Integer age = null;
    public String sex = null;
    public List<String> famMem = new ArrayList<>();
    public WorkExperience work = null;

    public Resume(String name) {
        this.name = name;
        work = new WorkExperience();
    }// Resume

    public void setName(String name) {
        this.name = name;
    }// setName

    public void setPersonal(String sex, int age, List<String> famMem) {
        this.age = age;
        this.sex = sex;
        this.famMem = famMem;
    }// setPersonal

    public void setWork(String timeArea, String company) {
        work.timeArea = timeArea;
        work.company = company;
    }// setWork

    /**
     * 重些clone()方法为public类型，并调用Object类的本地clone()方法。
     *
     * 浅拷贝，调用Object类中的clone
     */
    /*@Override
    public Resume clone() throws CloneNotSupportedException {
        return (Resume) super.clone();
    }*/

    /**
     * 重些clone()方法为public类型，为每个字段都创建新的对象，已实现深拷贝功能。
     */
    @Override
    public Resume clone() throws CloneNotSupportedException {
        int age = this.age;
        String sex = this.sex;
        String name = new String(this.name);
        ArrayList<String> famMem = new ArrayList<>(this.famMem);

        Resume copy = new Resume(name);
        copy.setPersonal(sex, age, famMem);
        copy.setWork(this.work.timeArea, this.work.company);
        return copy;
    }

    public void display() {
        System.out.println(this.name + " " + this.sex + " " + this.age);
        System.out.print("Family member: ");
        for(String elem : famMem) {
            System.out.print(elem + " ");
        }
        System.out.println();
        System.out.print("Work experience: " + this.work.timeArea);
        System.out.println(" " + this.work.company);
    }
}
