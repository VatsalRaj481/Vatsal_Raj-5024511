package test;

//ExcelDocumentFactory.java
class ExcelDocumentFactory extends DocumentFactory {
@Override
public Document createDocument() {
 return new ExcelDocument();
}
}
