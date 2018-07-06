package thread.localThread;

/**
 * @author guojiabin
 * @version 2018/7/6 0006 14:55
 */
public class PrintThreadInfo {
    public static void testThread1(){
        System.out.println("testThread1");
        for (int i = 0; i < Thread.currentThread().getStackTrace().length; i++) {
            System.out.println(Thread.currentThread().getStackTrace()[i]);
        }
        System.out.println("==========");
        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("==========");
        testThread2();
    }
    public static void testThread2(){
        System.out.println("testThread2");
        for (int i = 0; i < Thread.currentThread().getStackTrace().length; i++) {
            System.out.println(Thread.currentThread().getStackTrace()[i]);
        }
        System.out.println("==========");
        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()
                +":"+Thread.currentThread().getStackTrace()[1].getLineNumber());
        System.out.println("===========");
        testThread3();
    }
    public static void testThread3(){
        System.out.println("testThread3");
        for (int i = 0; i < Thread.currentThread().getStackTrace().length; i++) {
            System.out.println(Thread.currentThread().getStackTrace()[i]);
        }
        System.out.println("==========");
        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()
                +":"+Thread.currentThread().getStackTrace()[1].getLineNumber());
        System.out.println("===========");
    }
    public static void main(String[] args) {
        testThread1();
    }
}
