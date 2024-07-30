package test;
//FactoryMethodTest.java
public class FactoryMethodTest {
public static void main(String[] args) {
   DocumentFactory wordFactory = new WordDocumentFactory();
   Document wordDoc = wordFactory.createDocument();
   wordDoc.open();

   DocumentFactory pdfFactory = new PdfDocumentFactory();
   Document pdfDoc = pdfFactory.createDocument();
   pdfDoc.open();
}
}
