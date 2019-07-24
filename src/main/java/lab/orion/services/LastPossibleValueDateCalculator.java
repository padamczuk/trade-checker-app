package lab.orion.services;

import lab.orion.consts.TradeCheckerConsts;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import static lab.orion.services.WorkingDaysService.getWorkingDaysShift;

public class LastPossibleValueDateCalculator {

    public static LocalDate calculateSpotValueDate(LocalDate tradeDate){
        return tradeDate.plusDays(getWorkingDaysShift(tradeDate, 2));
    }

    public static LocalDate calculateForwardValueDate(LocalDate tradeDate){
        List<LocalDate> dates = TradeCheckerConsts.FORWARD_TYPE_MONTHS
                .stream()
                .map(month -> getForwardDate(tradeDate.getYear(), month))
                .collect(Collectors.toList());
        return dates.stream()
                .filter(localDate -> localDate.isAfter(tradeDate))
                .findFirst()
                .orElseGet(() -> getForwardDate(tradeDate.getYear() + 1, Month.MARCH));
    }

    public static LocalDate getForwardDate(int year, Month month) {
        LocalDate quarter = LocalDate.of(year, month, 1);
        int diff = DayOfWeek.FRIDAY.ordinal() - quarter.getDayOfWeek().ordinal();
        if (diff <= 0) {
            quarter = quarter.plusWeeks(1);
        }
        quarter = quarter.plusDays(diff);
        return quarter.plusWeeks(2);
    }
}
