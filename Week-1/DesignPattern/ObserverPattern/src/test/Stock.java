package test;

//Stock.java (Subject)
interface Stock {
void registerObserver(Observer observer);
void removeObserver(Observer observer);
void notifyObservers();
}