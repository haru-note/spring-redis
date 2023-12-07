package net.harunote.springredis.sample;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CacheDataRequest {
    private String key;
    private String value;
}