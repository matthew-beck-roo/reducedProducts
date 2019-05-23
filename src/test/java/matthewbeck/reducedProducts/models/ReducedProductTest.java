package matthewbeck.reducedProducts.models;

import matthewbeck.reducedProducts.fixtures.ProductFixtures;
import org.junit.Test;

import java.util.Collections;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReducedProductTest {

    private final ReducedProduct reducedProduct = new ReducedProduct(ProductFixtures.PRODUCT1, "ShowWasNow");

    @Test
    public void isReducedTrueWhenWasHigherThanNow() {
        assertTrue(reducedProduct.isReduced());
    }

    @Test
    public void isReducedFalseWhenWasIsZero() {
        var reducedProduct = new ReducedProduct(
                new Product("1", "title", Collections.emptyList(), new Price(0.0, 20.0, 10.0, 5.0, "GBP")), "ShowWasNow");
        assertFalse(reducedProduct.isReduced());
    }

    @Test
    public void isReducedFalseWhenNowIsZero() {
        var reducedProduct = new ReducedProduct(
                new Product("1", "title", Collections.emptyList(), new Price(30.0, 20.0, 10.0, 0.0, "GBP")), "ShowWasNow");
        assertFalse(reducedProduct.isReduced());
    }

    @Test
    public void isReducedFalseWhenNowHigherThanWas() {
        var reducedProduct = new ReducedProduct(
                new Product("1", "title", Collections.emptyList(), new Price(30.0, 20.0, 10.0, 35.0, "GBP")), "ShowWasNow");
        assertFalse(reducedProduct.isReduced());
    }

    @Test
    public void getPriceReductionReturnsDifferenceBetweenWasAndNow() {
        assertThat(reducedProduct.getPriceReduction(), is(25.0));
    }

    @Test
    public void getNowPriceWhenBelowTenPoundDisplaysPence() {
        var reducedProduct = new ReducedProduct(
                new Product("1", "title", Collections.emptyList(), new Price(30.0, 20.0, 10.0, 5.0, "GBP")), "ShowWasNow");
        assertThat(reducedProduct.getNowPrice(), is("£5.00"));
    }

    @Test
    public void getNowPriceWhenAboveTenPoundAndNonIntegerDisplaysPence() {
        var reducedProduct = new ReducedProduct(
                new Product("1", "title", Collections.emptyList(), new Price(30.0, 20.0, 10.0, 15.20, "GBP")), "ShowWasNow");
        assertThat(reducedProduct.getNowPrice(), is("£15.20"));
    }

    @Test
    public void getNowPriceWhenAboveTenPoundAndIntegerDoesNotDisplaysPence() {
        var reducedProduct = new ReducedProduct(
                new Product("1", "title", Collections.emptyList(), new Price(30.0, 20.0, 10.0, 15.0, "GBP")), "ShowWasNow");
        assertThat(reducedProduct.getNowPrice(), is("£15"));
    }

    @Test
    public void getPriceLabelWhenShowWasNow() {
        var reducedProduct = new ReducedProduct(ProductFixtures.PRODUCT1, "ShowWasNow");
        assertThat(reducedProduct.getPriceLabel(), is("Was £30, now £5.00"));
    }

    @Test
    public void getPriceLabelWhenShowWasThenNow() {
        var reducedProduct = new ReducedProduct(ProductFixtures.PRODUCT1, "ShowWasThenNow");
        assertThat(reducedProduct.getPriceLabel(), is("Was £30, then £10, now £5.00"));
    }

    @Test
    public void getPriceLabelWhenShowWasThenNowNoThen2() {
        var reducedProduct = new ReducedProduct(
                new Product("1", "title", Collections.emptyList(), new Price(30.0, 20.0, 0.0, 15.0, "GBP")), "ShowWasThenNow");
        assertThat(reducedProduct.getPriceLabel(), is("Was £30, then £20, now £15"));
    }

    @Test
    public void getPriceLabelWhenShowWasThenNowNoThen() {
        var reducedProduct = new ReducedProduct(
                new Product("1", "title", Collections.emptyList(), new Price(30.0, 0.0, 0.0, 15.0, "GBP")), "ShowWasThenNow");
        assertThat(reducedProduct.getPriceLabel(), is("Was £30, now £15"));
    }

    @Test
    public void getPriceLabelWhenShowPercDscount() {
        var reducedProduct = new ReducedProduct(ProductFixtures.PRODUCT1, "ShowPercDscount");
        assertThat(reducedProduct.getPriceLabel(), is("83% off - now £5.00"));

    }

    @Test
    public void getPriceLabelDefaultIsShowWasNow() {
        var reducedProduct = new ReducedProduct(ProductFixtures.PRODUCT1, "Someting");
        assertThat(reducedProduct.getPriceLabel(), is("Was £30, now £5.00"));
    }
}
