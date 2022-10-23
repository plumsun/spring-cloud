package com.migu.remote;

import com.migu.remote.fallback.SSORemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(value = "cas", url = "${ticketAuthentication.url}", fallbackFactory = SSORemoteHystrix.class)
public interface SSORemote {


    @GetMapping(value = "/cas/serviceValidate")
    String serviceValidate(@RequestParam("service") String service,
                           @RequestParam("ticket") String ticket,
                           @RequestParam("format") String format);


    @GetMapping(value = "/cas/serviceValidate",consumes  = MediaType.APPLICATION_JSON_VALUE)
    String serviceValidateV1(@RequestParam("service") String service,
                           @RequestParam("ticket") String ticket,
                           @RequestParam("format") String format);

    @GetMapping(value = "/cas/serviceValidate",consumes  = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String serviceValidateV2(@RequestParam("service") String service,
                           @RequestParam("ticket") String ticket,
                           @RequestParam("format") String format);

    @GetMapping(value = "/cas/serviceValidate",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String serviceValidateV3(@RequestParam("service") String service,
                           @RequestParam("ticket") String ticket,
                           @RequestParam("format") String format);
}
