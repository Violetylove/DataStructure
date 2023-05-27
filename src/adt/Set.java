package adt;

/**
 * @author Winter Yuan
 * @version 1.0
 */
public interface Set<T> {
    public T search();
    public boolean add(T x);
    public T remove(T x);
}
