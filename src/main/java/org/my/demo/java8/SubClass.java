package org.my.demo.java8;

public class SubClass extends MyClass implements MyInterface,MyInterface2,MyInterface3{

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        // 类优先原则，父类方法与实现接口默认方法有同名，子类未实现是，父类方法优先于接口
        System.out.println(subClass.getName());

        System.out.println("----------------------");

        // 如果子类实现了两个或多个具有同名默认方法的接口，要求子类必须实现这个默认方法
        System.out.println(subClass.getSome());

        System.out.println("----------------------");
        MyInterface.show();
    }

    // 如果子类实现了两个或多个具有同名默认方法的接口，要求子类必须实现这个默认方法
    @Override
    public String getSome() {
        return "子类的 getSome";
    }
}
