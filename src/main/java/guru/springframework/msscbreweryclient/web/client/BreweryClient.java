package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Setter
@Component
@RequiredArgsConstructor
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer";

    private String apiHost;

    private final RestTemplate restTemplate;

    public BeerDto getBeerById(UUID id) {
        return restTemplate.getForObject(apiHost + BEER_PATH_V1 + id.toString(), BeerDto.class);
    }

}
