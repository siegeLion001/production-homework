package com.lhh.common.utils;

import java.util.HashMap;
import java.util.Map;

public class FastMap<K, V> extends HashMap<K, V> {

    public FastMap putChain(K key, V value) {
        super.put(key, value);
        return this;
    }

    public FastMap putAllChain(Map<? extends K, ? extends V> m) {
        super.putAll(m);
        return this;
    }
}
