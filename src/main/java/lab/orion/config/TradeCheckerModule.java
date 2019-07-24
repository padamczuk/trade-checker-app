package lab.orion.config;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import lab.orion.clients.FixerClient;
import lab.orion.clients.HolidaysClient;
import lab.orion.services.checker.*;
import lab.orion.services.dto.CheckError;
import lab.orion.services.dto.TradeCheckerItemDto;

public class TradeCheckerModule extends AbstractModule {

    @Override
    protected final void configure() {
        ResourceConfig rc = new PackagesResourceConfig("lab.orion.services");
        for (Class<?> resource : rc.getClasses()) {
            System.out.println("Binding resource :" + resource.getName());
            bind(resource).asEagerSingleton();
        }
        bind(FixerClient.class).asEagerSingleton();
        bind(HolidaysClient.class).asEagerSingleton();

        Multibinder<ViolationChecker<TradeCheckerItemDto, CheckError>> hasClassifiers = Multibinder.newSetBinder(binder(), new TypeLiteral<ViolationChecker<TradeCheckerItemDto, CheckError>>(){});
        ResourceConfig rc2 = new PackagesResourceConfig("lab.orion.services.checker");
        hasClassifiers.addBinding().to(CustomerViolationChecker.class);
        hasClassifiers.addBinding().to(ForwardValueDateViolationChecker.class);
        hasClassifiers.addBinding().to(UnsupportedLegalEntityViolationChecker.class);
        hasClassifiers.addBinding().to(OptionExcerciseStartDatePresentViolationChecker.class);
        hasClassifiers.addBinding().to(OptionExcerciseStartDateViolationChecker.class);
        hasClassifiers.addBinding().to(OptionExpiryDateViolationChecker.class);
        hasClassifiers.addBinding().to(OptionPremiumDateBeforeDeliveryDateViolationChecker.class);
        hasClassifiers.addBinding().to(OptionStyleViolationChecker.class);
        hasClassifiers.addBinding().to(SpotValueDateViolationChecker.class);
        hasClassifiers.addBinding().to(TradeDatePresentChecker.class);
        hasClassifiers.addBinding().to(ValueDatePresentChecker.class);
        hasClassifiers.addBinding().to(ValueDateNotBeforeTradeDateViolationChecker.class);
        hasClassifiers.addBinding().to(WorkingDayValueDateViolationChecker.class);
        hasClassifiers.addBinding().to(CurrencyPairPresentViolationChecker.class);
        hasClassifiers.addBinding().to(RateViolationChecker.class);
        hasClassifiers.addBinding().to(OptionDeliveryDatePresentViolationChecker.class);
        hasClassifiers.addBinding().to(OptionExcerciseStartDatePresentViolationChecker.class);
        hasClassifiers.addBinding().to(OptionExpiryDatePresentViolationChecker.class);
        hasClassifiers.addBinding().to(OptionExpiryDateViolationChecker.class);
        hasClassifiers.addBinding().to(OptionPremiumDatePresentViolationChecker.class);
        hasClassifiers.addBinding().to(OptionPremiumDateBeforeDeliveryDateViolationChecker.class);

    }
}
