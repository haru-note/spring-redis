package net.harunote.springredis.rediskey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;
import net.harunote.springredis.keygenerate.KeyGenerateService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author CodeVillains description :
 */

@SpringBootTest
public class RedisKeyServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(RedisKeyServiceTest.class);

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private KeyGenerateService keyGenerateService;

    @Mock
    private ValueOperations<String, String> valueOperations; // Mock ValueOperations


    @InjectMocks
    private RedisKeyService redisKeyService;

    @Test
    void testGetAllKeys() {
        // Mocked set of keys
        Set<String> mockKeys = new HashSet<>();
        mockKeys.add("key1");
        mockKeys.add("key2");
        mockKeys.add("key3");

        // Mocking RedisTemplate's keys("*") method to return mockKeys
        when(redisTemplate.keys("*")).thenReturn(mockKeys);

        // Calling the method
        Set<String> retrievedKeys = redisKeyService.getAllKeys();

        // Asserting that the retrieved keys match the mockKeys
        assertEquals(mockKeys, retrievedKeys);
    }

    @Test
    void testSave() throws NoSuchMethodException {
        Integer number = 123;
        String value = "testValue";
        String expectedKey = "expectedKey";

        when(keyGenerateService.generateKey(number, value)).thenReturn(expectedKey);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations); // Mock opsForValue()

        redisKeyService.save(number, value);

        verify(keyGenerateService, times(1)).generateKey(number, value);
        verify(redisTemplate.opsForValue(), times(1)).set(expectedKey, "Example Data for " + expectedKey);
    }

    @Test
    void testSearch() throws NoSuchMethodException {
        // given
        Integer number = 123;
        String value = "testValue";
        String expectedKey = "expectedKey";
        String expectedResult = "expectedResult";

        // given
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(keyGenerateService.generateKey(number, value)).thenReturn(expectedKey);
        when(valueOperations.get(expectedKey)).thenReturn(expectedResult);

        // Calling the method to be tested
        String result = redisKeyService.search(number, value);

        // then
        verify(keyGenerateService).generateKey(number, value);
        verify(redisTemplate.opsForValue()).get(expectedKey);

        // Asserting that the returned result matches the expected value
        assertEquals(expectedResult, result);
    }

}