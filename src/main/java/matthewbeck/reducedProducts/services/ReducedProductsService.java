package matthewbeck.reducedProducts.services;

import matthewbeck.reducedProducts.clients.ProductsClient;
import matthewbeck.reducedProducts.models.Products;
import matthewbeck.reducedProducts.models.ReducedProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class ReducedProductsService {

    private final ProductsClient productsClient;

    @Autowired
    public ReducedProductsService(ProductsClient productsClient) {
        this.productsClient = productsClient;
    }

    public Products getReducedProducts(int categoryId, String labelType) {
        return new Products(productsClient.getProducts(categoryId).stream()
                .map(p -> new ReducedProduct(p, labelType))
                .filter(ReducedProduct::isReduced)
                .sorted(Comparator.comparing(ReducedProduct::getPriceReduction).reversed())
                .collect(Collectors.toList()));
    }
}
