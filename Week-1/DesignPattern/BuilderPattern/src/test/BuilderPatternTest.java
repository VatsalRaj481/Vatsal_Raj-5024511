package test;
//BuilderPatternTest.java
public class BuilderPatternTest {
 public static void main(String[] args) {
     Computer computer = new Computer.Builder("Intel i7", "16GB")
                         .setStorage("512GB SSD")
                         .setGraphicsCard(true)
                         .build();

     System.out.println(computer);
 }
}