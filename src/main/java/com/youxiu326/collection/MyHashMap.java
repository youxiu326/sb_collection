package com.youxiu326.collection;

import com.youxiu326.inter.MyMap;

/**
 * 纯手写HashMap集合
 * 1.7 HashMap 数组加链表结构
 *
 * @param <K>
 * @param <V>
 */
public class MyHashMap<K, V> implements MyMap<K, V> {

    // 1.定义table 存放HasMap 数组元素 默认是没有初始化容器 懒加载
    Node<K, V>[] table = null;

    // 2.实际使用table 存储容量大小
    int size;

    // 3.HashMap默认负载因子，负载因子越小，hash冲突机率越低， 根据每个链表的个数
    float DEFAULT_LOAD_FACTOR = 0.75f;

    // 4.table默认初始大小 16
    static int DEFAULT_INITIAL_CAPACITY = 16; //  aka 1 << 4

    @Override
    public V put(K key, V value) {

        // 1.判断table数组大小是否为空 ，空就初始化
        if (table == null) {
            table = new Node[DEFAULT_INITIAL_CAPACITY];
        }

        // 2. hashMap 扩容机制 为什么要扩容？扩容数组之后，有什么影响？ hahsmap 中是从什么时候开始扩容
        // 实际存储大小=负载因子*初始容量=DEFAULT_LOAD_FACTOR0.75*DEFAULT_INITIAL_CAPACITY16=12
        // 如果size>12的时候就需要开始扩容数组,扩容数组大小之前两倍
        if (size > (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY)) {
            // 需要开始对table进行属数组扩容
            resize();
        }

        // 2. 通过取模算法 获得下标

        int hashCode = key.hashCode();
        int index = hashCode % DEFAULT_INITIAL_CAPACITY;

        Node<K, V> node = table[index];
        if (node == null){
            // 没有发生hash冲突问题
            node = new Node<>(key, value, node);
        }else {
            Node<K,V> newNode = node;
            while (newNode!=null){
                // equals 比较字符串     == 比较值引用
                if (newNode.getKey().equals(key) || newNode.getKey()==key){
                    // 修改 value
                    return newNode.setValue(value);
                } else {
                    // 已经遍历到最后一个node，添加将最新node添加至最前面
                    if (newNode.next == null){
                        node = new Node(key,value,node);
                        size++;
                    }
                }
                newNode = newNode.next;
            }
        }
        table[index] = node;
        return null;
    }

    @Override
    public V get(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % DEFAULT_INITIAL_CAPACITY;
        Node<K,V> node = getNode(table[index],key);

        return node == null ? null : node.value;
    }
    public Node<K, V> getNode(Node<K, V> node, K k) {
        while (node != null) {
            if (node.getKey().equals(k)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    // 对table进行扩容
    private void resize() {

        // 1.生成新的table 是之前的两倍
        Node<K, V>[] newTable =new Node[DEFAULT_INITIAL_CAPACITY<<1];
        // 2.重新计算index索引，存放在新的table里面
        for (int i = 0; i < table.length; i++) {
            Node<K, V> oldNode = table[i];
            while (oldNode!=null){
                table[i] = null;//垃圾回收
                K oldKey = oldNode.getKey();
                int index = getIndex(oldKey, newTable.length);



            }
        }

    }

    /**
     * 计算下标
     * @param k
     * @param length
     * @return
     */
    public int getIndex(K k,int length){
        int hashCode = k.hashCode();
        int index = hashCode% length;
        return index;
    }

    @Override
    public int size() {
        return 0;
    }

    // 定义节点
    class Node<K, V> implements Entry<K, V> {
        // 存放Map 集合 key
        private K key;
        // 存放Map 集合 value
        private V value;
        // 下一个节点Node
        private Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            super();
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V value) {
            // 设置新值的返回老的 值
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

    }

    /**
     *  测试方法.打印所有的链表元素
     */
    public void print() {

        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            System.out.print("下标位置[" + i + "]");
            while (node != null) {
                System.out.print("[ key:" + node.getKey() + ",value:" + node.getValue() + "]");
                node = node.next;
                // if (node.next != null) {
                // node = node.next;
                // } else {
                // // 结束循环
                // node = null;
                // }
            }
            System.out.println();
        }

    }

}