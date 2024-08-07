package test;

//Image.java
interface Image {
 void display();
}

//RealImage.java (Real Subject)
class RealImage implements Image {
 private String filename;

 public RealImage(String filename) {
     this.filename = filename;
     loadFromDisk();
 }

 private void loadFromDisk() {
     System.out.println("Loading " + filename);
 }

 @Override
 public void display() {
     System.out.println("Displaying " + filename);
 }
}

//ProxyImage.java (Proxy)
class ProxyImage implements Image {
 private RealImage realImage;
 private String filename;

 public ProxyImage(String filename) {
     this.filename = filename;
 }

 @Override
 public void display() {
     if (realImage == null) {
         realImage = new RealImage(filename);
     }
     realImage.display();
 }
}

//ProxyPatternTest.java
public class ProxyPatternTest {
 public static void main(String[] args) {
     Image image = new ProxyImage("test_image.jpg");
     
     // Image will be loaded from disk
     image.display();
     System.out.println("");

     // Image will not be loaded from disk
     image.display();
 }
}
