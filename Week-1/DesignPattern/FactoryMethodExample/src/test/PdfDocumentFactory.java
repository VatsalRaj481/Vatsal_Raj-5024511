package test;

//PdfDocumentFactory.java
class PdfDocumentFactory extends DocumentFactory {
@Override
public Document createDocument() {
 return new PdfDocument();
}
}
