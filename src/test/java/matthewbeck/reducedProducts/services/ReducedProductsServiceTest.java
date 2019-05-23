package matthewbeck.reducedProducts.services;

import matthewbeck.reducedProducts.clients.ProductsClient;
import matthewbeck.reducedProducts.fixtures.ProductFixtures;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReducedProductsServiceTest {

    @Mock
    private ProductsClient productsClient;

    @InjectMocks
    private ReducedProductsService reducedProductsService;

    @Test
    public void getReducedProductsReturnsReducedProductsOnlyHighestReductionFirst() {
        when(productsClient.getProducts(101)).thenReturn(ProductFixtures.PRODUCTS.getProducts());
        var reducedProducts = reducedProductsService.getReducedProducts(101, "");

        assertThat(reducedProducts.getProducts(), hasSize(2));
        assertThat(reducedProducts.getProducts().get(0).getProductId(),
                is(ProductFixtures.PRODUCT2.getProductId()));
    }
}
