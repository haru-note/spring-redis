package net.harunote.springredis.sample;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author CodeVillains description :
 */
@Slf4j
@Service
public class RedisSampleService {

    @Cacheable(value = "myCache")
    public String getCachedData(String key) {
        log.info("# get key = {}", key);
        return "Cached Data for key: " + key;
    }

    @CachePut(value = "myCache", key = "#key")
    public String saveToCache(String key, String value) {
        log.info("# save key = {}, value = {}", key, value);
        // This method is used to save a value to the cache explicitly
        // It is annotated with @CachePut to update the cache with the provided key-value pair
        return value;
    }

}
