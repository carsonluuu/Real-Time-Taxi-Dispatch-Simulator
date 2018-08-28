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
package demo.service.impl;

import demo.model.CurrentPosition;
import demo.model.ServiceLocation;
import demo.model.VehicleStatus;
import demo.service.ServiceLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 *
 *
 */
@Service
public class DefaultServiceLocationService implements ServiceLocationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultServiceLocationService.class);

    //    @Autowired
//    @LoadBalanced
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Enriches the provided {@link CurrentPosition} with the closest service location
     * IF the {@link CurrentPosition} has a {@link VehicleStatus} of:
     * <p>
     * <ul>
     * <li>{@link VehicleStatus#SERVICE_NOW}</li>
     * <li>{@link VehicleStatus#SERVICE_SOON}</li>
     * <li>{@link VehicleStatus#STOP_NOW}</li>
     * <ul>
     *
     * @param currentPosition Will be enriched with the closest service location
     * @throws Exception
     */
//	@HystrixCommand(
//			commandKey="serviceLocations",
//			commandProperties = {
//					@HystrixProperty(name = "circuitBreaker.forceOpen", value = "true")
//			},
//			fallbackMethod = "handleServiceLocationServiceFailure")
    @Override
    public void updateServiceLocations(CurrentPosition currentPosition) {

        switch (currentPosition.getVehicleStatus()) {

            case SERVICE_NOW:
            case SERVICE_SOON:
            case STOP_NOW:
                ResponseEntity<Resource<ServiceLocation>> result = this.restTemplate.exchange(
                        "http://service-location-service/serviceLocations/search/findFirstByLocationNear?location={lat},{long}",
                        HttpMethod.GET, new HttpEntity<Void>((Void) null),
                        new ParameterizedTypeReference<Resource<ServiceLocation>>() {
                        }, currentPosition.getLocation().getLatitude(),
                        currentPosition.getLocation().getLongitude());
                if (result.getStatusCode() == HttpStatus.OK
                        && result.getBody().getContent() != null) {
                    currentPosition.setServiceLocation(result.getBody().getContent());
                }
                break;
            default:
        }

    }

    public void handleServiceLocationServiceFailure(CurrentPosition currentPosition) {
        LOGGER.error("Hystrix Fallback Method. Unable to retrieve service station info.");
    }
}
