/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.model.CurrentPosition;
import demo.service.ServiceLocationService;

/**
 * Spring Cloud Stream {@link Sink}, responsible for sending current position data to
 * connected Websocket clients.
 *
 *
 *
 */
@MessageEndpoint
@EnableBinding(Sink.class)
public class FleetLocationUpdaterSink {

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private ObjectMapper objectMapper;

	@ServiceActivator(inputChannel = Sink.INPUT)
	public void updateLocationaddServiceLocations(String input) throws Exception {

		CurrentPosition payload = this.objectMapper.readValue(input, CurrentPosition.class);

		this.template.convertAndSend("/topic/vehicles", payload);
	}

}
