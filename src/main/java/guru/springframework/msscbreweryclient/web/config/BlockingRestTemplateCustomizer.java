package guru.springframework.msscbreweryclient.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final Integer maxTotalConnection;

    private final Integer defaultMaxTotalConnection;

    private final Integer connectionRequestTimeout;

    private final Integer socketTimeout;

    public BlockingRestTemplateCustomizer(@Value("${sfg.customizer.max-total-connection}") Integer maxTotalConnection,
                                          @Value("${sfg.customizer.default-max-total-connection}") Integer defaultMaxTotalConnection,
                                          @Value("${sfg.customizer.connection-request-timeout}") Integer connectionRequestTimeout,
                                          @Value("${sfg.customizer.socket-timeout}") Integer socketTimeout) {
        this.maxTotalConnection = maxTotalConnection;
        this.defaultMaxTotalConnection = defaultMaxTotalConnection;
        this.connectionRequestTimeout = connectionRequestTimeout;
        this.socketTimeout = socketTimeout;
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnection);
        connectionManager.setDefaultMaxPerRoute(defaultMaxTotalConnection);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

}

