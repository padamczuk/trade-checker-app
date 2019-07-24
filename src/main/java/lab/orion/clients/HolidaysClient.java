package lab.orion.clients;

import com.google.inject.Inject;
//import com.hp.cache4guice.Cached;
import lab.orion.clients.dto.Holiday;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class HolidaysClient {
    private static final String REST_URI = "https://kayaposoft.com/enrico/json/v2.0";
    private ClientConfig clientConfig;
    private Client client;

    @Inject
    public HolidaysClient(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
        client = ClientBuilder.newBuilder()
                .withConfig(clientConfig)
                .build();
    }

    /**
     * https://www.kayaposoft.com/enrico/json/
     */
//    @Cached
    public List<Holiday> getHolidaysForYear(Locale locale, int year) {
        //https://kayaposoft.com/enrico/json/v2.0/?action=getHolidaysForYear&year=2019&country=pl&holidayType=public_holiday
        List<Holiday> holidays = client
                .target(REST_URI)
                .queryParam("action", "getHolidaysForYear")
                .queryParam("year", year)
                .queryParam("country", locale.getCountry())
                .queryParam("holidayType", "public_holiday")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Holiday>>(){});
        return holidays;
    }
//    @Cached
    public Set<LocalDate> getHolidaysDatesForYear(Locale locale, int year) {
        List<Holiday> holidays = getHolidaysForYear(locale, year);
        Set<LocalDate> holidayDates = holidays.stream()
                .map(holiday -> holiday.getDate())
                .collect(Collectors.toSet());
        return holidayDates;
    }
}
