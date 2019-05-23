package matthewbeck.reducedProducts.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorSwatch {

    private static final Map<String, String> RGB_VALUES = Map.of(
            "Blue", "0000ff",
            "Black", "000000",
            "Green", "008000",
            "Red", "ff0000",
            "White", "ffffff",
            "Purple", "800080",
            "Grey", "808080",
            "Pink", "F0A1C2",
            "Orange", "ffa500",
            "Yellow", "ffff00");

    private final String color;
    private final String basicColor;
    private final String skuId;

    public ColorSwatch(String color, String basicColor, String skuId) {
        this.color = color;
        this.basicColor = basicColor;
        this.skuId = skuId;
    }

    public String getColor() {
        return color;
    }

    public String getSkuId() {
        return skuId;
    }

    public String getRgbColor() {
        return RGB_VALUES.getOrDefault(basicColor, "000000");
    }
}
