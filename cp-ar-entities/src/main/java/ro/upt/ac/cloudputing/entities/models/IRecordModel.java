package ro.upt.ac.cloudputing.entities.models;

import java.util.Map;

public interface IRecordModel extends Map<String, Object> {
    boolean containsNotNull(String key);

    boolean isNullOrAbsent(String key);

    boolean getBoolean(String key);
}
