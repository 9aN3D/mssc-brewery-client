package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
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
    void getBeerById() {
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
}
