package test;

//StockMarket.java (Concrete Subject)
import java.util.ArrayList;
import java.util.List;

class StockMarket implements Stock {
 private List<Observer> observers;
 private String stockPrice;

 public StockMarket() {
     observers = new ArrayList<>();
 }

 @Override
 public void registerObserver(Observer observer) {
     observers.add(observer);
 }

 @Override
 public void removeObserver(Observer observer) {
     observers.remove(observer);
 }

 @Override
 public void notifyObservers() {
     for (Observer observer : observers) {
         observer.update(stockPrice);
     }
 }

 public void setStockPrice(String stockPrice) {
     this.stockPrice = stockPrice;
     notifyObservers();
 }
}

//MobileApp.java (Concrete Observer)
class MobileApp implements Observer {
 private String name;

 public MobileApp(String name) {
     this.name = name;
 }

 @Override
 public void update(String stockPrice) {
     System.out.println(name + " received stock price update: " + stockPrice);
 }
}

//WebApp.java (Concrete Observer)
class WebApp implements Observer {
 private String name;

 public WebApp(String name) {
     this.name = name;
 }

 @Override
 public void update(String stockPrice) {
     System.out.println(name + " received stock price update: " + stockPrice);
 }
}

//ObserverPatternTest.java
public class ObserverPatternTest {
 public static void main(String[] args) {
     StockMarket stockMarket = new StockMarket();

     Observer mobileApp = new MobileApp("Mobile App");
     Observer webApp = new WebApp("Web App");

     stockMarket.registerObserver(mobileApp);
     stockMarket.registerObserver(webApp);

     stockMarket.setStockPrice("100 USD");
     stockMarket.setStockPrice("101 USD");
 }
}
