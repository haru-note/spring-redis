package net.harunote.springredis.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CodeVillains description :
 */
@Slf4j
@RestController
// @RequestMapping("/api/v1")
public class RedisSampleController {

    private final RedisSampleService sampleCacheService;

    public RedisSampleController(RedisSampleService redisSampleService) {
        this.sampleCacheService = redisSampleService;
    }


    @GetMapping("/hello")
    public String helloWorld(@RequestParam String name) {
        log.info("Hello world = {}", name);
        return "Hello " + name;
    }

    @GetMapping("/cached/{key}")
    public String getRedisCache(@PathVariable String key) {
        // Call the getCachedData method of CacheService
        log.info("cached key = {}", key);
        return sampleCacheService.getCachedData(key);
    }

    @PostMapping("/save-to-cache")
    public String saveToCache(@RequestBody CacheDataRequest request) {
        // Call the saveToCache method of CacheService
        return sampleCacheService.saveToCache(request.getKey(), request.getValue());
    }

}
