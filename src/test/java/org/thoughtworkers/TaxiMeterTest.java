package org.thoughtworkers;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.thoughtworkers.Meter.dayLightMeter;
import static org.thoughtworkers.Meter.nightMeter;

public class TaxiMeterTest {
    @Test
    public void shouldReturnBasePriceIfDistanceIsNoMoreThanThreeKMs() throws Exception {
        Meter meter = dayLightMeter(false);
        assertThat(meter.calculate(2), is(8.0));
    }

    @Test
    public void shouldReturnCalculatedPriceIfDistanceIsMoreThanThreeKMs() throws Exception {
        Meter meter = dayLightMeter(false);
        assertThat(meter.calculate(5), is(12.0));

    }

    @Test
    public void shouldReturnNightBasePriceIfDistanceIsNoMoreThanThreeKMs() throws Exception {
        Meter meter = nightMeter();
        assertThat(meter.calculate(2), is(10.0));
    }

    @Test
    public void shouldReturnCalculatedNightPriceIfDistanceIsMoreThanThreeKMs() throws Exception {
        Meter nightMeter = nightMeter();
        assertThat(nightMeter.calculate(4), is(13.0));
    }

    @Test
    public void shouldReturn6IfDistanceIs2ForSuburbTrip() throws Exception {
        Meter meter = dayLightMeter(true);
        assertThat(meter.calculate(2), is(6.0));
    }

    @Test
    public void shouldReturn9IfDistanceIs5ForSuburbTrip() throws Exception {
        Meter meter = dayLightMeter(true);
        assertThat(meter.calculate(5), is(9.0));
    }

    @Test
    public void shouldReturn26IfDistanceIs2ForCrossRegionTrip() throws Exception {
        Meter meter = dayLightMeter(true);
        double price = meter.calculate(2, 1);
        assertThat(price, is(26.0));
    }

    @Test
    public void shouldReturn30IfDistanceIs5ForCrossRegionTrip() throws Exception {
        Meter meter = dayLightMeter(true);
        assertThat(meter.calculate(3, 2), is(30.0));
    }

    @Test
    public void shouldReturn30IfDistanceIs5ForAnotherCrossRegionTrip() throws Exception {
        Meter meter = dayLightMeter(true);
        assertThat(meter.calculate(2, 3), is(30.0));
    }
}
