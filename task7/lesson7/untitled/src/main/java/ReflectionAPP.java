import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;


public class ReflectionAPP {
    @MarkingAnnotation
    public void markedMethod() {
        System.out.println("Java");
    }

    public static void main(String[] args) {

        try {
            Method m = MainClass.class.getMethod("advAnnotatedMethod", null);
            AdvancedAnnotation annotation = m.getAnnotation(AdvancedAnnotation.class);
            System.out.println("value: " + annotation.value());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Method[] methods = Cat.class.getDeclaredMethods();
        for (Method o : methods) {
            if(o.getAnnotation(Test.class) != null) {
                System.out.println(o);
            }
        }


    }
}