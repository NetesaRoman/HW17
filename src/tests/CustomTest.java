package tests;

/*
 *
 * @author Roman Netesa
 * Unit test class
 */


import tests.api.*;

public class CustomTest {


    @BeforeSuite
    public static void beforeSuite(){
        System.out.println("Before Suite Done");
    }

    @Test
    public static void test1(){
        System.out.println("Test 1 done");
    }

    @Test(id = 2)
    public static void test2(){
        System.out.println("Test 2 done");
    }

    @Test(id = 3)
    public static void test3(){
        System.out.println("Test 3 done");
    }

    @Test(id = 4)
    public static void test4(){
        System.out.println("Test 4 done");
    }

    @AfterSuite
    public static void afterSuite(){
        System.out.println("After Suite Done");
    }

//    @AfterSuite
//    public static void afterSuiteTESTDEBUG(){
//        System.out.println("After Suite Done");
//    }


}
