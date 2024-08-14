package test;

//WordDocumentFactory.java
class WordDocumentFactory extends DocumentFactory {
@Override
public Document createDocument() {
 return new WordDocument();
}
}
