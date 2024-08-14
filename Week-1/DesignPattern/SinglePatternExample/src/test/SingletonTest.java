package test;
// SingletonTest.java
public class SingletonTest {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("This is a singleton pattern example.");
        
        // Check if both instances are the same
        System.out.println(logger1 == logger2);  // Should print true
    }
}
