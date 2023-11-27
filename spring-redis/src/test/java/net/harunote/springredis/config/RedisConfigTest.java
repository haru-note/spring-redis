package net.harunote.springredis.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.interceptor.KeyGenerator;

/**
 * @author CodeVillains description :
 */


@SpringBootTest
class RedisConfigTest {
    private static final Logger logger = LoggerFactory.getLogger(RedisConfigTest.class);

    @Test
    void testKeyGenerator() throws NoSuchMethodException {
        RedisConfig redisConfig = new RedisConfig();
        KeyGenerator keyGenerator = redisConfig.keyGenerator();

        // 모의로 사용할 객체, 메서드, 매개변수 생성
        Object target = new Object();
        Method method = target.getClass().getMethod("toString");
        Object[] params = {"param1", "param2"};

        // keyGenerator의 generate() 메서드 호출
        Object generatedKey = keyGenerator.generate(target, method, params);

        // 예상되는 키 생성 방식과 일치하는지 확인
        StringBuilder expectedKey = new StringBuilder();
        expectedKey.append(target.getClass().getName()).append("-");
        expectedKey.append(method.getName()).append("-");
        expectedKey.append(Arrays.toString(params));

        // 예상된 해시코드와 일치하는지 확인
        int expectedHashCode = Objects.hash(expectedKey.toString());
        logger.info("# expectedHashCodeTest = {}", expectedHashCode);
        assertEquals(expectedHashCode, generatedKey);
    }
}