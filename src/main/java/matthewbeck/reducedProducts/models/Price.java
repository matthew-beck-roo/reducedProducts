package matthewbeck.reducedProducts.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {

    private final double was;
    private final double then1;
    private final double then2;
    private final double now;
    private final String currency;

    public Price(double was, double then1, double then2, Object now, String currency) {
        this.was = was;
        this.then1 = then1;
        this.then2 = then2;
        this.now = parseNow(now);
        this.currency = currency;
    }

    public double getWas() {
        return was;
    }

    public double getThen1() {
        return then1;
    }

    public double getThen2() {
        return then2;
    }

    public double getNow() {
        return now;
    }

    public String getCurrency() {
        return currency;
    }

    private double parseNow(Object now) {
        try {
            return Double.parseDouble(now.toString());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
