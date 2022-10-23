package com.migu.remote.fallback;

import com.migu.gvpcore.util.LogUtil;
import com.migu.remote.SSORemote;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


@Component
public class SSORemoteHystrix implements FallbackFactory<SSORemote> {

    @Override
    public SSORemote create(Throwable throwable) {
        return new SSORemote() {
            @Override
            public String serviceValidate(String service, String ticket, String format) {
                LogUtil.error(throwable, "service:" + service + "ticket:" + ticket + "format:" + format);
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
