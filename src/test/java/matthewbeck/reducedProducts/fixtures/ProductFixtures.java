package matthewbeck.reducedProducts.fixtures;

import matthewbeck.reducedProducts.models.ColorSwatch;
import matthewbeck.reducedProducts.models.Price;
import matthewbeck.reducedProducts.models.Product;
import matthewbeck.reducedProducts.models.Products;

import java.util.Collections;
import java.util.List;

public class ProductFixtures {

    public static ColorSwatch COLOR_SWATCH1 = new ColorSwatch("Bright Yellow", "Yellow", "124423");
    public static ColorSwatch COLOR_SWATCH2 = new ColorSwatch("Dim Pink", "Pink", "221233");

    public static Price PRICE1 = new Price(30.0, 20.0, 10.0, 5.0, "GBP");
    public static Price PRICE2 = new Price(30.0, 0.0, 0.0, 1.0, "GBP");
    public static Price PRICE3 = new Price(0.0, 0.0, 0.0, 5.0, "GBP");

    public static Product PRODUCT1 = new Product("1", "Floral Dress", List.of(COLOR_SWATCH1), PRICE1);
    public static Product PRODUCT2 = new Product("2", "Lace Dress", List.of(COLOR_SWATCH1, COLOR_SWATCH2), PRICE2);
    public static Product PRODUCT3 = new Product("3", "Skirt", Collections.emptyList(), PRICE3);

    public static Products PRODUCTS = new Products(List.of(PRODUCT1, PRODUCT2, PRODUCT3));
}
