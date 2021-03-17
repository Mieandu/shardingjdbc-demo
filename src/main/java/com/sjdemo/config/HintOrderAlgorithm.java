package com.sjdemo.config;

import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

public class HintOrderAlgorithm implements HintShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedList<>();
        for (String each : availableTargetNames) {
            for (Long value : shardingValue.getValues()) {
                if (each.endsWith(String.valueOf(value % 2))) {
                    result.add(each);
                }
            }
        }
        return result;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return "HINT_TEST";
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void setProps(Properties properties) {

    }

    public static void main(String[] args) {
        String hashs= "wm9JmOBgAAApyzMMpYc5542gi0iWnEpA144";
        System.out.println(hash(hashs) %64);

    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
