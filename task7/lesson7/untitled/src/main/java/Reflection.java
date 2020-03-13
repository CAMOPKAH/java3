import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflection {
    public static void main(String[] args) {
        Class catClass = Cat.class;
        Field[] publicFields = catClass.getFields();
        for (Field o : publicFields) {
            System.out.println("Тип_поля Имя_поля : " + o.getType().getName() + " " + o.getName());
        }
        try {
            Cat cat = new Cat("xxx","red", 5);
            Field fieldName = cat.getClass().getField("name");
            fieldName.set(cat, "Мурзик");
            Field fieldAge = cat.getClass().getField("age");
            System.out.println(fieldAge.get(cat));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Constructor[] constructors = Cat.class.getConstructors();
        for (Constructor o : constructors) {
            System.out.println(o);
        }
        System.out.println("---");
        try {
            System.out.println(Cat.class.getConstructor(new Class[] {String.class, int.class}));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        /*------------------------*/
        Method[] methods = Cat.class.getDeclaredMethods();
        for (Method o : methods) {
            System.out.println(o.getReturnType() + " ||| " + o.getName() + " ||| " + Arrays.toString(o.getParameterTypes()));
        }
        try {
            Method m1 = Cat.class.getMethod("jump", null);
            Method m2 = Cat.class.getMethod("meow", int.class);
            System.out.println(m1 + " | " + m2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        /*------------------------*/
        Cat cat = new Cat("Barsik");
        try {
            Method mMeow = Cat.class.getDeclaredMethod("meow", int.class);
            mMeow.invoke(cat, 5);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        /*---------------------*/

        try {
            Class someClass = Cat.class;
            Constructor catCounstructor = Cat.class.getConstructor(String.class, String.class, int.class);
           // Cat cat1 = (Cat)someClass.newInstance();
            Cat cat2 = (Cat)catCounstructor.newInstance("Murzik", "Black", 3);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}

