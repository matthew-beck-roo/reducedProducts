package matthewbeck.reducedProducts.clients;

import matthewbeck.reducedProducts.fixtures.ProductFixtures;
import matthewbeck.reducedProducts.models.Products;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductsClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ProductsClient productsClient;

    @Test
    public void returnProductsWhenThereAreProducts() {
        when(restTemplate.getForObject("https://jl-nonprod-syst.apigee.net/v1/categories/101/products?key=2ALHCAAs6ikGRBoy6eTHA58RaG097Fma",
                Products.class)).thenReturn(ProductFixtures.PRODUCTS);

        var products = productsClient.getProducts(101);
        assertThat(products, hasSize(3));
    }

    @Test
    public void returnEmptyListWhenNullFromRestTemplate() {
        when(restTemplate.getForObject("https://jl-nonprod-syst.apigee.net/v1/categories/101/products?key=2ALHCAAs6ikGRBoy6eTHA58RaG097Fma",
                Products.class)).thenReturn(null);

        var products = productsClient.getProducts(101);
        assertThat(products, hasSize(0));
    }
}
