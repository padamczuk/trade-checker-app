package lab.orion.services;

import com.google.inject.Inject;
import lab.orion.clients.HolidaysClient;
import lab.orion.consts.TradeCheckerConsts;

import java.time.LocalDate;
import java.util.Set;

public class WorkingDaysService {
    private HolidaysClient holidaysClient;

    @Inject
    public WorkingDaysService(HolidaysClient holidaysClient) {
        this.holidaysClient = holidaysClient;
    }

    public boolean isWorkingDay(LocalDate valueDate) {
        Set<LocalDate> holidayDates = holidaysClient.getHolidaysDatesForYear(TradeCheckerConsts.POLAND, valueDate.getYear());
        return !TradeCheckerConsts.WEEKEND.contains(valueDate.getDayOfWeek()) && !holidayDates.contains(valueDate);
    }

    public static long getWorkingDaysShift(LocalDate tradeDate, int days) {
        long counter = 0;
        for (int i = 0; i < days; i++) {
            tradeDate = tradeDate.plusDays(1);
            counter++;
            while (TradeCheckerConsts.WEEKEND.contains(tradeDate.getDayOfWeek())) {
                tradeDate = tradeDate.plusDays(1);
                counter++;
            }
        }
        return counter;
    }

}
