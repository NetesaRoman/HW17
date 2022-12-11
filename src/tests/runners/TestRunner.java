package tests.runners;
/*
 *
 * @author Roman Netesa
 *
 */


import tests.CustomTest;
import tests.api.AfterSuite;
import tests.api.BeforeSuite;
import tests.api.Test;
import tests.api.ToMuchAnnotationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TestRunner {
    public static void main(String[] args) {

        CustomTest ct = new CustomTest();
        Class<? extends CustomTest> cl = ct.getClass();

        try {
            start(cl, ct);
        } catch (ToMuchAnnotationException e) {
            e.printStackTrace();
        }
    }

    public static void start(Class<? extends CustomTest> cl, CustomTest ct) throws ToMuchAnnotationException {

        exceptionHandling(cl, ct);
        //BeforeSuiteCall
        invokeByAnnotation(cl, ct, BeforeSuite.class);
        //Tests
        invokeTests(cl, ct, 1, 10);
        //AfterSuiteCall
        invokeByAnnotation(cl, ct, AfterSuite.class);
    }

    private static void exceptionHandling(Class<? extends CustomTest> cl,
                                          CustomTest ct) throws ToMuchAnnotationException {

        if (cl == null || ct == null) {
            System.out.println("Careful, NullPointerException lies ahead!");
        }

        int before = 0;
        int after = 0;

        for (Method method : cl.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                before++;
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                after++;
            }
        }

        handleAnnotations(before, after);
    }

    private static void invokeByAnnotation(Class<? extends CustomTest> cl,
                                           CustomTest ct,
                                           Class<? extends Annotation> annotation) {

        for (Method method : cl.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                try {
                    method.invoke(ct);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static void invokeTests(Class<? extends CustomTest> cl, CustomTest ct, int from, int to) {
        if(from > to){
            System.out.println("error!");
            return;
        }

        for (int i = from; i <= to; i++) {
            for (Method method : cl.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Test.class) && method.getAnnotation(Test.class).id() == i) {
                    try {
                        method.invoke(ct);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }



    private static void handleAnnotations(int before, int after) throws ToMuchAnnotationException {
        if (before >= 2) {
            throw new ToMuchAnnotationException("BeforeSuite");
        } else if (after >= 2) {
            throw new ToMuchAnnotationException("AfterSuite");
        }
    }
}
