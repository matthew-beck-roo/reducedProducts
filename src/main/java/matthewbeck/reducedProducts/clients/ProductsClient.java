package matthewbeck.reducedProducts.clients;

import matthewbeck.reducedProducts.models.Product;
import matthewbeck.reducedProducts.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsClient {

    private static final String PRODUCTS_ENDPOINT = "https://jl-nonprod-syst.apigee.net/v1/categories/{categoryId}/products";
    private static final String API_KEY = "2ALHCAAs6ikGRBoy6eTHA58RaG097Fma";
    private static final String PRODUCTS_URL = String.format("%s?key=%s", PRODUCTS_ENDPOINT, API_KEY);

    private final RestTemplate restTemplate;

    @Autowired
    public ProductsClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> getProducts(int categoryId) {
        return Optional.ofNullable(restTemplate.getForObject(PRODUCTS_URL.replace("{categoryId}", String.valueOf(categoryId)),
                Products.class)).map(Products::getProducts).orElse(Collections.emptyList());
    }
}
