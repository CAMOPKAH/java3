package GunitTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

//Альтерантивный тетсировщик
public class AltTest {

    AltMethod BMethod;
    AltMethod AMethod;
    ArrayList<AltMethod> TestMethods = new ArrayList<>();

    //Чистим методы
    public void Clear(){
        BMethod = null;
        AMethod = null;
        TestMethods = new ArrayList<>();
    }

    public void Invoke(AltMethod altMethod, Object obj, Class aClass ) {

    }
    public void Start(Class testClass) {
        Clear(); //Чистим методы

        Method[] methods = testClass.getDeclaredMethods();
        for (Method o : methods) {
            if (o.getAnnotation(BeforeSuite.class) != null) {
                if (BMethod!=null) throw new RuntimeException("Error double anotation BeforeSuit");
                BMethod = new AltMethod(o, o.getName());
                System.out.println( "set Before:"+ o);
            }
            else if (o.getAnnotation(AfterSuite.class) != null) {
                if (AMethod!=null) throw new RuntimeException("Error double anotation AfterSuit");
                AMethod = new AltMethod(o, o.getName());
                System.out.println( "set Before:"+ o);
            }
            else if (o.getAnnotation(Test.class) != null) {
                Test tst = o.getAnnotation(Test.class);

                TestMethods.add(new AltMethod(o, o.getName(), tst.Priority()));
                System.out.println("ADD:" + o);
            }
        }
        TestMethods.sort(Comparator.comparing(AltMethod::getPriority));

        //Запускаем метод ДО
        Object obj;
        try {
            System.out.println("Create");
            Constructor conClass = testClass.getConstructor(null);
            obj = conClass.newInstance();//Запускаем конструктор объекта
            System.out.println("BeforeSuit");
            BMethod.Invoke(obj);
            for (AltMethod testMethod : TestMethods) {
                System.out.println("Invoke: " + testMethod.MethodName + " " + testMethod.getPriority());
                testMethod.Invoke(obj);
            }
            System.out.println("AfterSuit");
            AMethod.Invoke(obj);
        }
        catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

    }
}

