package lab.orion.clients.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;
import java.util.List;

public class Holiday {
    @JsonDeserialize(using = HolidayLocalDateDeserializer.class)
    private LocalDate date;
    private List<HolidayName> name;
    private String holidayType;

    public LocalDate getDate() {
        return date;
    }

    public List<HolidayName> getName() {
        return name;
    }

    public String getHolidayType() {
        return holidayType;
    }
}
