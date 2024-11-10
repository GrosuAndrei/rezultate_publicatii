package ro.upt.ac.cloudputing.entities.models;

import java.util.TreeMap;

public class SortedRecordModel extends TreeMap<String, Object> implements IRecordModel {

    public SortedRecordModel() {
    }

    public boolean containsNotNull(String key) {
        return this.containsKey(key) && this.get(key) != null;
    }

    public boolean isNullOrAbsent(String key) {
        return !this.containsNotNull(key);
    }
    public boolean getBoolean(String key) {
        if (!this.containsNotNull(key)) return false;
        var value = this.get(key);
        return value instanceof Boolean bVal ? bVal : false;
    }
}
