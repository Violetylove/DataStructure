package adt;

/**
 * @author Winter Yuan
 * @version 1.0
 */
public interface Set<T> {
    public T search(T value);
    public boolean add(T value);
    public T remove(T value);
}
