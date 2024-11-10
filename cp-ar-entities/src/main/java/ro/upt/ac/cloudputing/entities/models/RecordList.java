package ro.upt.ac.cloudputing.entities.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecordList extends ArrayList<IRecordModel> {

    public List<Map<String, Object>> convertToArrayList() {
        return this.stream().map(record -> (Map<String, Object>) record).toList();
    }
}
