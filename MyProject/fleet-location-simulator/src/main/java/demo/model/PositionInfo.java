/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package demo.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO to hold position of the vehicle and some other data.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PositionInfo {

    private String vin;
    private Point position;
    private VehicleStatus vehicleStatus = VehicleStatus.NONE;

    /**
     * kml path is composed of a series of legs (line segments) 1 .. n.
     * this member denotes the present leg (starting at leg 0)
     */
    private Leg leg;

    /**
     * Meters from start of leg
     */
    private Double distanceFromStart;

    /**
     * The speed in m/s
     */
    private Double speed;

}
