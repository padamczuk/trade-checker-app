package lab.orion.clients.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;

import java.util.List;
import java.util.Map;

public class Holidays {
    private Integer status;
    @JsonSerialize(keyUsing = MapSerializer.class)
    private Map<String, String> requests;
    private List<Holiday> holidays;

    public Integer getStatus() {
        return status;
    }

    public Map<String, String> getRequests() {
        return requests;
    }

    public List<Holiday> getHolidays() {
        return holidays;
    }
}
