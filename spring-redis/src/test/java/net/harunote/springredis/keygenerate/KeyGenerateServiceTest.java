package net.harunote.springredis.keygenerate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.interceptor.KeyGenerator;

@SpringBootTest
class KeyGenerateServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(KeyGenerateServiceTest.class);
    @Test
    void testSetKeyGenerator() throws NoSuchMethodException {
        KeyGenerator keyGenerator = mock(KeyGenerator.class);
        KeyGenerateService keyGenerateService = new KeyGenerateService(keyGenerator);

        // 모의로 사용할 값 설정
        Integer number = 123;
        String value = "value";

        // keyGenerator.generate() 메서드의 반환 값 설정
        when(keyGenerator.generate(any(), any(), anyInt(), anyString())).thenReturn("expectedKey");

        // setKeyGenerator 메서드 호출
        keyGenerateService.setKeyGenerator(number, value);

        // keyGenerator.generate() 메서드가 올바른 인자로 호출되었는지 확인
        verify(keyGenerator, times(1)).generate(any(), any(), eq(number), eq(value));
    }

    @Test
    void testGenerateKey() throws NoSuchMethodException {
        KeyGenerator keyGenerator = mock(KeyGenerator.class);
        KeyGenerateService keyGenerateService = new KeyGenerateService(keyGenerator);

        // 모의로 사용할 값 설정
        Integer number = 123;
        String value = "value";

        // keyGenerator.generate() 메서드의 반환 값 설정
        when(keyGenerator.generate(any(), any(), anyInt(), anyString())).thenReturn("expectedKey");

        // generateKey 메서드 호출
        String generatedKey = keyGenerateService.generateKey(number, value);

        // keyGenerator.generate() 메서드가 올바른 인자로 호출되었는지 확인
        verify(keyGenerator, times(1)).generate(any(), any(), eq(number), eq(value));

        // 생성된 키의 반환 값 확인
        assertEquals("expectedKey", generatedKey);
    }
}
