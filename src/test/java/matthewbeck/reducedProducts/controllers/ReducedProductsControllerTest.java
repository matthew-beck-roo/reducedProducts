package matthewbeck.reducedProducts.controllers;

import matthewbeck.reducedProducts.fixtures.ProductFixtures;
import matthewbeck.reducedProducts.services.ReducedProductsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReducedProductsController.class)
public class ReducedProductsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReducedProductsService reducedProductsService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void reducedProductsEndpointReturnsJsonWithProducts() throws Exception {
        given(reducedProductsService.getReducedProducts(101, "ShowWasNow")).willReturn(ProductFixtures.PRODUCTS);

        mvc.perform(get("/categories/101/reducedProducts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products", hasSize(3)))
                .andExpect(jsonPath("$.products[0].productId", is("1")))
                .andExpect(jsonPath("$.products[1].productId", is("2")))
                .andExpect(jsonPath("$.products[2].productId", is("3")));
    }
}
