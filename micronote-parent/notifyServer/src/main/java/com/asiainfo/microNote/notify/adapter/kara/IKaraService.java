package com.asiainfo.microNote.notify.adapter.kara;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="karaService", url = "${kara.karaService}")
public interface IKaraService {
	
	@RequestMapping(path = "${kara.incomingUri}",headers={"Authorization=${kara.accessToken}","content-type=application/json"}, method = RequestMethod.POST)
	String incoming(KaraIncoming karaIncoming);

}
