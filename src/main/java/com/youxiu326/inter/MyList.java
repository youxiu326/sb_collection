package com.youxiu326.inter;

/**
 * 自定义List 接口
 * @param <E>
 */
public interface MyList<E> {

    public boolean add(E object);

    public boolean  add(int index, E object);

    public Object remove(int index);

    public boolean remove(E object);

    public int getSize();

    public Object get(int index);

    public Object[] toArray();
} 