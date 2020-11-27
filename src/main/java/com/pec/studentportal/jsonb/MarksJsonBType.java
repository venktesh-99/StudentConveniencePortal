package com.pec.studentportal.jsonb;

import com.pec.studentportal.pojo.MarksDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarksJsonBType extends JsonBType {
    @Override
    public Class<Map> returnedClass() {
        Map<String, List<MarksDetail>> stringListMap = new HashMap<>();
        return Map.class;
    }
}
