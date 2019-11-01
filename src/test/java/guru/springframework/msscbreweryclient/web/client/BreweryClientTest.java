package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void testGetBeerById() {
        BeerDto beer = client.getBeerById(UUID.randomUUID());

        assertNotNull(beer);
    }

    @Test
    void testSaveNewBeer() {
        BeerDto beer = BeerDto.builder().beerName("New beer test").build();
        URI uri = client.saveNewBeer(beer);

        assertNotNull(uri);

        System.out.println(uri);
    }

    @Test
    void testUpdateBeer() {
        //given
        BeerDto beer = BeerDto.builder().beerName("Update beer test").build();

        client.updateBeer(UUID.randomUUID(), beer);
    }

    @Test
    void testDeleteBeer() {
        client.deleteBeer(UUID.randomUUID());
    }

    @Test
    void testGetCustomerById() {
        CustomerDto customer = client.getCustomerById(UUID.randomUUID());

        assertNotNull(customer);
    }

    @Test
    void testSaveNewCustomer() {
        CustomerDto customer = CustomerDto.builder().name("Vania").build();
        URI uri = client.saveNewCustomer(customer);

        assertNotNull(uri);

        System.out.println(uri);
    }

    @Test
    void testUpdateCustomer() {
        //given
        CustomerDto customer = CustomerDto.builder().name("Pedro").build();

        client.updateCustomer(UUID.randomUUID(), customer);
    }

    @Test
    void testDeleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }

}
