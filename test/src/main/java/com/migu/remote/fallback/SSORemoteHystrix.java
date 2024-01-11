package com.migu.remote.fallback;

import com.migu.remote.SSORemote;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class SSORemoteHystrix implements FallbackFactory<SSORemote> {

    @Override
    public SSORemote create(Throwable throwable) {
        return new SSORemote() {
            @Override
            public String serviceValidate(String service, String ticket, String format) {
                log.error("service:{} ticket:{} format:{}", service, ticket, format, throwable);
                return null;
            }

            @Override
            public String serviceValidateV1(String service, String ticket, String format) {
                return null;
            }

            @Override
            public String serviceValidateV2(String service, String ticket, String format) {
                return null;
            }

            @Override
            public String serviceValidateV3(String service, String ticket, String format) {
                return null;
            }
        };
    }
}
