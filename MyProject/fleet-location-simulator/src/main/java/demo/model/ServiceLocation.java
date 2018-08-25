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

package demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

/**
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceLocation {

    private String id;
    private String address1;
    private String address2;
    private String city;
    @JsonUnwrapped
    private final Point point;
    private String location;
    private String state;
    private String zip;
    private String type;

    @SuppressWarnings("unused")
    private ServiceLocation() {
        this.point = new Point(0d, 0d);
    }

    @JsonCreator
    public ServiceLocation(@JsonProperty("latitude") double latitude, @JsonProperty("longitude") double longitude) {
        this.point = new Point(latitude, longitude);
    }

}
