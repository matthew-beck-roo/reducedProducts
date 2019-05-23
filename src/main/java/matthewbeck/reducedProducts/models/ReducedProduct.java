package matthewbeck.reducedProducts.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;
import java.util.function.Supplier;

public class ReducedProduct extends Product {

    private static final Map<String, String> CURRENCY_SYMBOLS = Map.of("GBP", "£");

    private final Map<String, Supplier<String>> priceLabels = Map.of(
            "ShowWasNow", this::showWasNow,
            "ShowWasThenNow", this::showWasThenNow,
            "ShowPercDscount", this::showPercDscount);

    private final String labelType;

    public ReducedProduct(Product p, String labelType) {
        super(p.getProductId(), p.getTitle(), p.getColorSwatches(), p.getPrice());
        this.labelType = labelType;
    }

    @JsonIgnore
    public boolean isReduced() {
        return getPrice().getWas() > 0.0 && getPrice().getNow() > 0.0 && getPrice().getWas() > getPrice().getNow();
    }

    @JsonIgnore
    public double getPriceReduction() {
        return getPrice().getWas() - getPrice().getNow();
    }

    public String getNowPrice() {
        return formatPrice(getPrice().getNow());
    }

    public String getPriceLabel() {
        return priceLabels.getOrDefault(labelType, priceLabels.get("ShowWasNow")).get();
    }

    private String formatPrice(double price) {
        var priceFormat = ((price % 1) == 0 && price >= 10.00) ? "%s%.0f" : "%s%.2f";
        return String.format(priceFormat, CURRENCY_SYMBOLS.get(getPrice().getCurrency()), price);
    }

    private double getPercentageOff() {
        return ((getPrice().getWas() - getPrice().getNow()) / getPrice().getWas()) * 100;
    }

    private String showWasNow() {
        return String.format("Was %s, now %s", formatPrice(getPrice().getWas()), getNowPrice());
    }

    private String showWasThenNow() {
        double then = getPrice().getThen2() > 0.0 ? getPrice().getThen2() : getPrice().getThen1();
        return then > 0.0 ? String.format("Was %s, then %s, now %s", formatPrice(getPrice().getWas()),
                formatPrice(then), getNowPrice()) : showWasNow();
    }

    private String showPercDscount() {
        return String.format("%.0f%% off - now %s", getPercentageOff(), getNowPrice());
    }
}
