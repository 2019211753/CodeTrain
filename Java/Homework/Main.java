package Homework;

abstract class Bird {   //创建被匿名内部类继承的抽象类
    private String name;
    public String getName() {
        return name;
    }
    public String setname(String name) {
        return this.name=name;
    }
    public abstract int fly() ;
}

class Helloworld {
    public void test(Bird bird) {
        System.out.println(bird.getName()+"能够飞"+bird.fly());
    }
    public static void main(String[] args) {
        Helloworld helloworld=new Helloworld();
        int a = 1;
        helloworld.test(   //格式如下 参数即为内部类 并且在内部类中实现继承的父类的所有抽象方法
                new Bird() {
                    public int fly() {
                        return 10000;
                    }
                    @Override
                    public String getName() {
                        return "大雁";
                    }
                }
        );
    }

}