package de.lfuhr.nfcdoorapp;

/**
 * Created by ludwig on 23.09.17.
 */

public abstract class Consumer<T> {
    public abstract void accept(T s);
}