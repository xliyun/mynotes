package com.liyun.DesignPattern.composite;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-18 9:45
 */
public class Client {
    public static void main(String[] args) {
        //从大到小创建对象
        OrganizationComponent university = new University("清华大学", "中国顶级大学");

        //创建学院
        OrganizationComponent computerColleage = new Colleage("计算机学院", "操作系统，数据结构，计算机网络");
        OrganizationComponent mathColleage = new Colleage("数学学院", "高等数学，概率论，离散数学");
        OrganizationComponent chemicalColleage = new Colleage("化学学院", "有机分子，催化剂，药剂学");

        //创建各个学院下的系
        computerColleage.add(new Department("软件工程","主攻ERP方向"));
        computerColleage.add(new Department("网络工程","主攻网络安全"));
        computerColleage.add(new Department("计算机科学与技术","啥都学，啥都不会"));

        mathColleage.add(new Department("数学与应用数学","培养掌握数学科学的基本理论、基础知识与基本方法,具有良好的科学素养和扎实"));
        mathColleage.add(new Department("数理基础科学","培养掌握数学科学的基本理论、基础知识与基本方法,具有良好的科学素养和扎"));

        chemicalColleage.add(new Department("应用化学","应用化学专业是专业培养具备化学方面的基础知识"));
        chemicalColleage.add(new Department("化学生物专业","化学生物学是研究生命过程中化学基础的科学"));

        //将学院加入到学校中
        university.add(computerColleage);
        university.add(chemicalColleage);
        university.add(mathColleage);

        university.print();
    }
}
