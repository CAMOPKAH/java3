import GunitTest.AfterSuite;
import GunitTest.BeforeSuite;
import GunitTest.Test;

public class Cat {
    public String name;
    public String color;
    public int age;
    private int vol=4;

    public Cat(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Cat(String name) {
        this.name = name;
    }

    public Cat(){
        System.out.println(this.getClass().getName() +  " constructor ");
    }
    @BeforeSuite
    public void pBefore() {
        vol = 5;
        System.out.println("-BeforeSuit");
    }

    @AfterSuite
    public void pAfter() {
        vol = 5;
        System.out.println("-AfterSuit");

    }

    @Test(Priority = 1)
    public void jump() {
        System.out.println("-JUMP");
    }


    @Test(Priority = 3)
    public void meow()
    {

        for (int i = 0; i < vol; i++) {
            System.out.print("-MAY ");

        }
        System.out.println("MAY.");
    }

    @Test(Priority = 2)
    public void run(){
        System.out.println("-RUN");
    }
}
