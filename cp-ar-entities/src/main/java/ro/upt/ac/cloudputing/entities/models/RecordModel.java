package ro.upt.ac.cloudputing.entities.models;

import java.util.LinkedHashMap;

public class RecordModel extends LinkedHashMap<String, Object> implements IRecordModel {
    public RecordModel(int initialCapacity) {
        super(initialCapacity);
    }

    public RecordModel() {
    }

    public boolean containsNotNull(String key) {
        if (!this.containsKey(key)) {
            return false;
        }
        var value = this.get(key);
        if (value == null) return false;
        if (value instanceof Integer nr) {
            return nr != 0;
        }
        if (value instanceof Float nr) {
            return nr != 0;
        }
        return true;
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
