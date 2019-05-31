
package it.sella.smb.chat.controller;

import org.apache.log4j.Logger;
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
	private static final Logger logger = Logger.getLogger(ChatController.class);


	@Value("${TOKEN}")
	private String TOKEN;
	@Autowired
	ChatFacade chatFacade;



	@PostMapping(path = "/webhook", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getMessage(@RequestBody WebhookRequest requestPayload	) {//@RequestHeader("X-Hub-Signature") final String signature
		logger.info("<<<<<<<<<FB Request payload:{} && FB signature: {}>>>>>>>>>>"+ requestPayload);
		if(requestPayload.getEntry().get(0).getMessaging().get(0).getMessage() != null && requestPayload.getEntry().get(0).getMessaging().get(0).getMessage().getText() != null) {
			logger.info("<<<<<<<<<Have Message in requestPayload>>>>>>>>>>");
			final Thread thread= new Thread() {
				@Override
				public void run() {
					logger.info("<<<<<<<<<Started to run thread>>>>>>>>>>");
					chatFacade.message(requestPayload);
				}
			};
			thread.start();
			logger.info("<<<<<<<<<Begin the execution of thread>>>>>>>>>>");
		}
		logger.info("<<<<<<<<<Send Acknowledgment>>>>>>>>>>");
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@GetMapping("/webhook")
	public ResponseEntity<?> verify(@RequestParam("hub.challenge") String challenge,
			@RequestParam("hub.verify_token") String token) {
		logger.info("<<<<<<<<<<<<<Hub Challenge is:{"+challenge+"} and token is {"+token+"}>>>>>>>>>>>>>");
		logger.info("<<<<<<<<<<<<<Thistoken is {"+this.TOKEN+"}>>>>>>>>>>>>>");
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
