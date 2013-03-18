package org.thoughtworkers;

public class Meter {
    private static final int DAYLIGHT_UNIT_PRICE = 2;
    private static final int BASE_DISTANCE = 3;
    private static final int DAYLIGHT_BASE_PRICE = 8;
    private static final int NIGHT_BASE_PRICE = 10;
    private static final int NIGHT_UNIT_PRICE = 3;
    private static final double EXTRA_PRICE = 20;

    private final int basePrice;
    private final double unitPrice;

    private Meter(int basePrice, double unitPrice) {
        this.basePrice = basePrice;
        this.unitPrice = unitPrice;
    }

    static Meter nightMeter() {
        return new Meter(NIGHT_BASE_PRICE, NIGHT_UNIT_PRICE);
    }

    static Meter dayLightMeter(boolean isSuburbTaxi) {
        if (isSuburbTaxi) {
            return new Meter(6, 1.5);
        }
        return new Meter(DAYLIGHT_BASE_PRICE, DAYLIGHT_UNIT_PRICE);
    }

    public double calculate(int distance) {
        if (distance <= BASE_DISTANCE) {
            return getBasePrice();
        }
        int exceedDistance = distance - BASE_DISTANCE;
        return getBasePrice() + exceedDistance * getUnitPrice();
    }

    private double getUnitPrice() {
        return unitPrice;

    }

    private int getBasePrice() {
        return basePrice;
    }

    public double calculate(int distanceInSuburb, int distanceInCity) {
        int totalInstance = distanceInSuburb + distanceInCity;
        if (totalInstance <= BASE_DISTANCE) {
            return calculate(totalInstance) + EXTRA_PRICE;
        }

        double priceInSuburb = calculate(distanceInSuburb);
        double priceInCity = distanceInCity * DAYLIGHT_UNIT_PRICE;
        return priceInSuburb + priceInCity + EXTRA_PRICE;
    }
}
