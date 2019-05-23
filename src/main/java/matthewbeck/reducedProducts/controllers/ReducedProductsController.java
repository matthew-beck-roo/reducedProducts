package matthewbeck.reducedProducts.controllers;

import matthewbeck.reducedProducts.models.Products;
import matthewbeck.reducedProducts.services.ReducedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories/{categoryId}/reducedProducts")
public class ReducedProductsController {

    private final ReducedProductsService reducedProductsService;

    @Autowired
    public ReducedProductsController(ReducedProductsService reducedProductsService) {
        this.reducedProductsService = reducedProductsService;
    }

    @GetMapping
    public Products getReducedProducts(@PathVariable int categoryId, @RequestParam(defaultValue = "ShowWasNow") String labelType) {
        return reducedProductsService.getReducedProducts(categoryId, labelType);
    }
}
