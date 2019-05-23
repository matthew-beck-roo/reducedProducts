package matthewbeck.reducedProducts.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final Price price;

    private final String productId;
    private final String title;
    private final List<ColorSwatch> colorSwatches;

    public Product(String productId, String title, List<ColorSwatch> colorSwatches, Price price) {
        this.productId = productId;
        this.title = title;
        this.colorSwatches = colorSwatches;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public List<ColorSwatch> getColorSwatches() {
        return colorSwatches;
    }

    public Price getPrice() {
        return price;
    }
}
