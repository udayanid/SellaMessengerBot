
package it.sella.smb.chat.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.sella.smb.chat.facade.ChatFacade;
import it.sella.smb.fb.dto.WebhookRequest;

@RestController
public class ChatController {
	private static final Log logger = LogFactory.getLog(ChatController.class);


	@Value("${TOKEN}")
	private String TOKEN;
	@Autowired
	ChatFacade chatFacade;



	@PostMapping(path = "/webhook", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getMessage(@RequestBody WebhookRequest requestPayload	) {//@RequestHeader("X-Hub-Signature") final String signature
		logger.info("<<<<<<<<<FB Request payload:{} && FB signature: {}>>>>>>>>>>"+ requestPayload);
		chatFacade.message(requestPayload);

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@GetMapping("/webhook")
	public ResponseEntity<?> verify(@RequestParam("hub.challenge") String challenge,
			@RequestParam("hub.verify_token") String token) {
		logger.info("<<<<<<<<<<<<<Hub Challenge is:{} and token is {}>>>>>>>>>>>>>"+ challenge+ token);
		if (token.equals(this.TOKEN)) {
			return new ResponseEntity<String>(challenge, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}


	/*
	 * Facebook RequestPayload:-
	 * {
	"object": "page",
	"entry": [{
		"id": "437062153490261",
		"time": 1539616619429,
		"messaging": [{
			"sender": {
				"id": "1841499292614128"
			},
			"recipient": {
				"id": "437062153490261"
			},
			"timestamp": 1539616618858,
			"message": {
				"mid": "F0LZKLRf0MQRHGQ6dYWTT4e0xl-rcOSVgGN5z_iUHtiBdMDf3S8XzLzrnz-rruC5Op_r4Bg2sBUpZb0_yGPGIw",
				"seq": 127,
				"text": "hi"
			}
		}]
	}]
}*/


}