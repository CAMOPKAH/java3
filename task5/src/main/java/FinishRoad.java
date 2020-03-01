import java.util.concurrent.Semaphore;

class FinishRoad extends Stage {
    final Object MON = new Object();
    public String FirstCarFinish=null;
    public int CountCars;
    Semaphore finish = new Semaphore(1);


    public FinishRoad(int CountCars, int length) {

        this.length = 0;

        this.CountCars=CountCars;

        this.length = length;
        this.description = "Дорога " + length + " метров";

    }
    @Override
    public void go(Car c) {


        System.out.println(c.getName() + " начал этап: " + description);
        try {
            Thread.sleep(length / c.getSpeed() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            finish.acquire();
            System.out.println(c.getName() + " закончил этап: " + description);
        CountCars--;
                    System.out.println(c.getName() + " WIN ");
                    if (FirstCarFinish==null) {
                        FirstCarFinish = c.getName();
                        System.out.println(c.getName()  + " победил!!!!");
                    }
                    if (CountCars == 0) {

                        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
                    }



    } catch (InterruptedException e) {
        e.printStackTrace();
    }
        finally {finish.release();}



    }
}