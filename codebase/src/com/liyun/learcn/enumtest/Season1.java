package com.liyun.learcn.enumtest;

//使用enum关键字枚举类
public enum Season1 implements Info{
    //默认 public static final
    //1.提供当前枚举类的对象，多个对象之间用  逗号 隔开，末尾对象用“分号”结束
    SPRING("春天","春暖花开"){
        @Override
        public void show() {
            System.out.println("春天在哪里？");
        }
    },
    SUMMER("夏天","夏日炎炎") {
        @Override
        public void show() {
            System.out.println("夏天夏天悄悄过去留下小秘密。");
        }
    },
    AUTUM("秋天","秋高气爽") {
        @Override
        public void show() {
            System.out.println("秋天不回来");
        }
    },
    WINTER("冬天","冰天雪地") {
        @Override
        public void show() {
            System.out.println("大约在冬季");
        }
    };

    //2.声明Season对象的属性：private final 修饰
    private final String sessonName;
    private final String sessonDesc;

    //3.私有化类的构造器，并给对象属性赋值
    Season1(String sessonName, String sessonDesc) {
        this.sessonName = sessonName;
        this.sessonDesc = sessonDesc;
    }

    //4.其他诉求1：获取枚举类对象的属性
    public String getSeasonName(){
        return sessonName;
    }

    public String getSessonDesc(){
        return sessonDesc;
    }


/*    @Override
    public void show() {
        System.out.println("这是"+sessonName);
    }*/
}
