package danieldjgomes.larica.infrastructure.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class AuthCache {
    private final Cache<String, RestauranteEntity> cache;

    public AuthCache() {
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(10))
                .maximumSize(1000)
                .build();

    }

    public void put(String key, RestauranteEntity value) {
        cache.put(key, value);
    }

    public RestauranteEntity get(String key) {
        return cache.getIfPresent(key);
    }

    public void remove(String key) {
        cache.invalidate(key);
    }
}