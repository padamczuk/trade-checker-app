package lab.orion.consts;

import com.google.common.collect.ImmutableList;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.EnumSet;
import java.util.Locale;

public class TradeCheckerConsts {

    public static final EnumSet<DayOfWeek> WEEKEND = EnumSet
            .of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
    public static final ImmutableList<Month> FORWARD_TYPE_MONTHS = ImmutableList.<Month>builder()
            .add(Month.MARCH, Month.JUNE, Month.SEPTEMBER, Month.DECEMBER).build();
    public static final Locale POLAND = new Locale("", "PL");

    private TradeCheckerConsts() {
    }
}
