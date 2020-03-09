package GunitTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AltMethod {
    Method method;
    String MethodName = "";
    int Priority = 0;

    public int getPriority() {
        return Priority;
    }
    public AltMethod(Method method, String MethodName) {
        this.method = method;
        this.MethodName = MethodName;
    }

    public AltMethod(Method method, String MethodName, int Priority) {
        this.method = method;
        this.MethodName = MethodName;
        this.Priority = Priority;
    }

    public void Invoke(Object obj) throws InvocationTargetException, IllegalAccessException {
        method.invoke(obj);
    }

}
