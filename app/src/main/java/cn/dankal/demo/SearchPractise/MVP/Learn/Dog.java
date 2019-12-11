package cn.dankal.demo.SearchPractise.MVP.Learn;

/**
 * @author
 * @date
 * @org
 * @email
 * @describe 1：定义一个静态builder类，它的成员变量与外部类的成员变量一致。
 * 2：builder类通过一系列方法用于给成员变量赋值，并返回当前的对象本身（this）
 * 3：在内部类创建一个build方法，它返回外部类对象(用于创建一个对应的外部类)，方法调用外部类的私有构造函数，
 * 参数是builder
 * 4：创建一个外部类的私有构造函数，构造函数的参数是builder类，
 * 外部类提供一个私有构造函数供内部类调用，并在构造函数里面完成成员变量的赋值，取值为builder对象对应的值。
 */

public class Dog {

    private int price;
    private int age;
    private double weight;
    private String name;

    private Dog(Builder builder) {
        this.price = builder.price;
        this.age = builder.age;
        this.weight = builder.weight;
        this.name = builder.name;
    }

    public int getPrice() {
        return price;
    }

    public Dog setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Dog setAge(int age) {
        this.age = age;
        return this;
    }

    public double getWeight() {
        return weight;
    }

    public Dog setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dog setName(String name) {
        this.name = name;
        return this;
    }

    static class Builder {

        private int price;
        private int age;
        private double weight;
        private String name;

        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setWeight(double weight) {
            this.weight = weight;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Dog build() {
            return new Dog(this);
        }
    }
}
