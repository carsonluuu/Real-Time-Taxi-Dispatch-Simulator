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

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonPropertyOrder({"numberOfGpsSimulatorRequests", "gpsSimulatorRequests"})
public class SimulatorFixture {

    private List<GpsSimulatorRequest> gpsSimulatorRequests = new ArrayList<>(0);

    public int getNumberOfGpsSimulatorRequests() {
        return gpsSimulatorRequests.size();
    }

    public void setGpsSimulatorRequests(List<GpsSimulatorRequest> gpsSimulatorRequests) {
        Assert.notEmpty(gpsSimulatorRequests, "gpsSimulatorRequests must not be empty.");
        this.gpsSimulatorRequests = gpsSimulatorRequests;
    }

    public boolean usesKmlIntegration() {
        return gpsSimulatorRequests.stream().anyMatch(request -> request.isExportPositionsToKml());
    }

}
