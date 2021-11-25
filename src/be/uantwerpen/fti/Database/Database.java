package be.uantwerpen.fti.Database;

public abstract class Database {
    public Database()
    {
    }

    public abstract void addEntry();
    public abstract void removeEntry();
    // To Do add observer
    //public abstract void addObserver(Observer o);
    public abstract void clearObservers();
    // https://www.java67.com/2013/02/10-examples-of-hashmap-in-java-programming-tutorial.html

}
