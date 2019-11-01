package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Setter
@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private String apiHost;

    private String apiBeerPathV1;

    private String apiCustomerPathV1;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID id) {
        return restTemplate.getForObject(apiHost + apiBeerPathV1 + id.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beer) {
        return restTemplate.postForLocation(apiHost + apiBeerPathV1, beer);
    }

    public void updateBeer(UUID id, BeerDto beer) {
        restTemplate.put(apiHost + apiBeerPathV1 + id.toString(), beer);
    }

    public void deleteBeer(UUID id) {
        restTemplate.delete(apiHost + apiBeerPathV1 + id.toString());
    }

    public CustomerDto getCustomerById(UUID id) {
        return restTemplate.getForObject(apiHost + apiCustomerPathV1 + id.toString(), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customer) {
        return restTemplate.postForLocation(apiHost + apiCustomerPathV1, customer);
    }

    public void updateCustomer(UUID id, CustomerDto customer) {
        restTemplate.put(apiHost + apiCustomerPathV1 + id.toString(), customer);
    }

    public void deleteCustomer(UUID id) {
        restTemplate.delete(apiHost + apiCustomerPathV1 + id.toString());
    }

}
