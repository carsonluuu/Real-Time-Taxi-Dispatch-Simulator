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
package demo.support;

import demo.model.FaultCode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility to provide random {@link FaultCode}s.
 */
public class FaultCodeUtils {

    /**
     * Private Constructor.
     * Suppress default constructor for non-instantiability
     */
    private FaultCodeUtils() {
        throw new AssertionError();
    }

    public static final List<FaultCode> faultCodes = new ArrayList<>(5);

    static {
        final FaultCode faultCode1 = new FaultCode();
        faultCode1.setEngineMake("DET");
        faultCode1.setFaultCode("LTP");
        faultCode1.setFaultCodeId("DET");
        faultCode1.setFaultCodeClassification("ServiceInfo");
        faultCode1.setDescription("Low Tire Pressure Warning");
        faultCode1.setRepairInstructions("Re-inflate tires as soon as possible");
        faultCode1.setFmi("14");
        faultCode1.setSa(null);
        faultCode1.setSpn("3696");

        final FaultCode faultCode2 = new FaultCode();
        faultCode2.setEngineMake("DET");
        faultCode2.setFaultCode("MJR");
        faultCode2.setFaultCodeId("DET");
        faultCode2.setFaultCodeClassification("ServiceSoon");
        faultCode2.setDescription("Engine Failure");
        faultCode2.setRepairInstructions("Tow or haul to nearest service station for replacement");
        faultCode2.setFmi("31");
        faultCode2.setSa(null);
        faultCode2.setSpn("3719");

        final FaultCode faultCode3 = new FaultCode();
        faultCode3.setEngineMake("DET");
        faultCode3.setFaultCode("FMW");
        faultCode3.setFaultCodeId("DET");
        faultCode3.setFaultCodeClassification("ServiceInfo");
        faultCode3.setDescription("Firmware Upgrade Required");
        faultCode3.setRepairInstructions("Verify Software update has been completed if available for this engine.");
        faultCode3.setFmi("14");
        faultCode3.setSa(null);
        faultCode3.setSpn("171");

        final FaultCode faultCode4 = new FaultCode();
        faultCode4.setEngineMake("DET");
        faultCode4.setFaultCode("OHT");
        faultCode4.setFaultCodeId("DET");
        faultCode4.setFaultCodeClassification("ServiceSoon");
        faultCode4.setDescription("Overheating Warning");
        faultCode4.setRepairInstructions("Cool it down with some ice from freezer");
        faultCode4.setFmi("15");
        faultCode4.setSa(null);
        faultCode4.setSpn("3719");

        final FaultCode faultCode5 = new FaultCode();
        faultCode5.setEngineMake("DET");
        faultCode5.setFaultCode("LOP");
        faultCode5.setFaultCodeId("DET");
        faultCode5.setFaultCodeClassification("StopTruck");
        faultCode5.setDescription("Low oil pressure warning");
        faultCode5.setRepairInstructions("Refill oil and check for leaks");
        faultCode5.setFmi("7");
        faultCode5.setSa(null);
        faultCode5.setSpn("4334");

        faultCodes.add(faultCode1);
        faultCodes.add(faultCode2);
        faultCodes.add(faultCode3);
        faultCodes.add(faultCode4);
        faultCodes.add(faultCode5);

    }

    ;

    public static FaultCode getRandomFaultCode() {
        return faultCodes.get(ThreadLocalRandom.current().nextInt(faultCodes.size()));
    }

}
