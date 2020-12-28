package application;

public interface Dao<T> {
    void add(T t);
    void update(T t);
}
