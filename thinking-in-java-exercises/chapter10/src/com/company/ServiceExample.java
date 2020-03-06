package com.company;

interface Service {
    void method1();
    void method2();
}

interface ServiceFactory {
    Service service();
}

class Implementation1 implements Service {

    @Override
    public void method1() {
        System.out.println("Implementaion1.method1()");
    }

    @Override
    public void method2() {
        System.out.println("Implementaion1.method2()");
    }

    public static ServiceFactory factory = new ServiceFactory() {
        @Override
        public Service service() {
            return new Implementation1();
        }
    };
}

class Implementation2 implements Service {

    @Override
    public void method1() {
        System.out.println("Implementaion2.method1()");
    }

    @Override
    public void method2() {
        System.out.println("Implementaion2.method2()");
    }

    public static ServiceFactory factory = new ServiceFactory() {
        @Override
        public Service service() {
            return new Implementation2();
        }
    };
}

public class ServiceExample {
    public static void consumer(ServiceFactory factory) {
        Service service = factory.service();
        service.method1();
        service.method2();
    }

    public static void main(String[] args) {
        consumer(Implementation1.factory);
        consumer(Implementation2.factory);
    }
}
