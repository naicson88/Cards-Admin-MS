package com.naicson.yugioh.feing;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "YuGiOh-API", url = "http://yugiohprices.com/api")
public interface UpdatePrice {
	
}
