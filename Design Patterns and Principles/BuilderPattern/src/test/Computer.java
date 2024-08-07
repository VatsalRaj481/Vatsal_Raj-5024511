package test;
//Computer.java
public class Computer {
 // Required parameters
 private String CPU;
 private String RAM;
 
 // Optional parameters
 private String storage;
 private boolean hasGraphicsCard;

 private Computer(Builder builder) {
     this.CPU = builder.CPU;
     this.RAM = builder.RAM;
     this.storage = builder.storage;
     this.hasGraphicsCard = builder.hasGraphicsCard;
 }

 public static class Builder {
     private String CPU;
     private String RAM;
     private String storage;
     private boolean hasGraphicsCard;

     public Builder(String CPU, String RAM) {
         this.CPU = CPU;
         this.RAM = RAM;
     }

     public Builder setStorage(String storage) {
         this.storage = storage;
         return this;
     }

     public Builder setGraphicsCard(boolean hasGraphicsCard) {
         this.hasGraphicsCard = hasGraphicsCard;
         return this;
     }

     public Computer build() {
         return new Computer(this);
     }
 }

 @Override
 public String toString() {
     return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", storage=" + storage + ", hasGraphicsCard=" + hasGraphicsCard + "]";
 }
}
