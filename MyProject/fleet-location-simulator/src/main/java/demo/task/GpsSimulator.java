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
package demo.task;

import demo.model.*;
import demo.service.PositionService;
import demo.support.NavUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Simulates a vehicle moving along a path defined in a kml file.
 */
public class GpsSimulator implements Runnable {

    private long id;

    private PositionService positionInfoService;

    private AtomicBoolean cancel = new AtomicBoolean();

    private Double speedInMps; // In meters/sec
    private boolean shouldMove;
    private boolean exportPositionsToKml = false;
    private boolean exportPositionsToMessaging = true;

    private Integer reportInterval = 500; // millisecs at which to send position reports
    private PositionInfo positionInfo = null;
    private List<Leg> legs;
    private VehicleStatus vehicleStatus = VehicleStatus.NONE;
    private String vin;

    private Integer secondsToError = 45;
    private Point startPoint;
    private Date executionStartTime;
    private FaultCode faultCode;

    public GpsSimulator(GpsSimulatorRequest gpsSimulatorRequest) {
        this.shouldMove = gpsSimulatorRequest.isMove();
        this.exportPositionsToKml = gpsSimulatorRequest.isExportPositionsToKml();
        this.exportPositionsToMessaging = gpsSimulatorRequest.isExportPositionsToMessaging();
        this.setSpeedInKph(gpsSimulatorRequest.getSpeedInKph());
        this.reportInterval = gpsSimulatorRequest.getReportInterval();

        this.secondsToError = gpsSimulatorRequest.getSecondsToError();
        this.vin = gpsSimulatorRequest.getVin();
        this.vehicleStatus = gpsSimulatorRequest.getVehicleStatus();
        this.faultCode = gpsSimulatorRequest.getFaultCode();
    }

    @Override
    public void run() {
        try {
            executionStartTime = new Date();
            if (cancel.get()) {
                destroy();
                return;
            }
            while (!Thread.interrupted()) {
                long startTime = new Date().getTime();
                if (positionInfo != null) {
                    if (shouldMove) {
                        moveVehicle();
                        positionInfo.setSpeed(speedInMps);
                    } else {
                        positionInfo.setSpeed(0d);
                    }

                    if (this.secondsToError > 0 && startTime - executionStartTime.getTime() >= this.secondsToError * 1000) {
                        this.vehicleStatus = VehicleStatus.SERVICE_NOW;
                    }

                    positionInfo.setVehicleStatus(this.vehicleStatus);

                    final FaultCode faultCodeToUse;

                    switch (this.vehicleStatus) {
                        case SERVICE_INFO:
                        case SERVICE_SOON:
                        case STOP_NOW:
                            faultCodeToUse = this.faultCode;
                            break;
                        default:
                            faultCodeToUse = null;
                            break;
                    }

                    final CurrentPosition currentPosition = new CurrentPosition(
                            positionInfo.getVin(),
                            new Point(positionInfo.getPosition().getLatitude(), positionInfo.getPosition().getLongitude()),
                            positionInfo.getVehicleStatus(),
                            positionInfo.getSpeed(),
                            positionInfo.getLeg().getHeading(),
                            faultCodeToUse);
                    positionInfoService.processPositionInfo(id, currentPosition, this.exportPositionsToKml, this.exportPositionsToMessaging);

                }

                // wait till next position report is due
                sleep(startTime);
            }
        } catch (InterruptedException ie) {
            destroy();
            return;
        }

        destroy();
    }

    /**
     * On thread interrupt. Send null position to all consumers to indicate that
     * sim has closed.
     */
    void destroy() {
        positionInfo = null;
    }

    /**
     * Sleep till next position report is due.
     *
     * @param startTime
     * @throws InterruptedException
     */
    private void sleep(long startTime) throws InterruptedException {
        long endTime = new Date().getTime();
        long elapsedTime = endTime - startTime;
        long sleepTime = reportInterval - elapsedTime > 0 ? reportInterval - elapsedTime : 0;
        Thread.sleep(sleepTime);
    }

    /**
     * Set new position of vehicle based on current position and vehicle speed.
     */
    void moveVehicle() {
        Double distance = speedInMps * reportInterval / 1000.0;
        Double distanceFromStart = positionInfo.getDistanceFromStart() + distance;
        Double excess = 0.0; // amount by which next postion will exceed end
        // point of present leg

        for (int i = positionInfo.getLeg().getId(); i < legs.size(); i++) {
            Leg currentLeg = legs.get(i);
            excess = distanceFromStart > currentLeg.getLength() ? distanceFromStart - currentLeg.getLength() : 0.0;

            if (Double.doubleToRawLongBits(excess) == 0) {
                // this means new position falls within current leg
                positionInfo.setDistanceFromStart(distanceFromStart);
                positionInfo.setLeg(currentLeg);
                Point newPosition = NavUtils.getPosition(currentLeg.getStartPosition(), distanceFromStart,
                        currentLeg.getHeading());
                positionInfo.setPosition(newPosition);
                return;
            }
            distanceFromStart = excess;
        }

        // if we've reached here it means vehicle has moved beyond end of path
        // so go back to start of path
        setStartPosition();
    }

    /**
     * Position vehicle at start of path.
     */
    public void setStartPosition() {
        positionInfo = new PositionInfo();
        positionInfo.setVin(this.vin);
        Leg leg = legs.get(0);
        positionInfo.setLeg(leg);
        positionInfo.setPosition(leg.getStartPosition());
        positionInfo.setDistanceFromStart(0.0);
    }

    /**
     * @return the speed
     */
    public Double getSpeedInMps() {
        return speedInMps;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeedInMps(Double speed) {
        this.speedInMps = speed;
    }

    public void setSpeedInKph(Double speed) {
        this.speedInMps = speed / 3.6;
    }

    public Double getSpeedInKph() {
        return this.speedInMps * 3.6;
    }

    /**
     * @return the shouldMove
     */
    public Boolean getShouldMove() {
        return shouldMove;
    }

    /**
     * @param shouldMove the shouldMove to set
     */
    public void setShouldMove(Boolean shouldMove) {
        this.shouldMove = shouldMove;
    }

    public synchronized void cancel() {
        this.cancel.set(true);
    }

    public void setExportPositionsToKml(boolean exportPositionsToKml) {
        this.exportPositionsToKml = exportPositionsToKml;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public PositionInfo getCurrentPosition() {
        return positionInfo;
    }

    public void setCurrentPosition(PositionInfo currentPosition) {
        this.positionInfo = currentPosition;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public Integer getSecondsToError() {
        return secondsToError;
    }

    public void setSecondsToError(Integer secondsToError) {
        this.secondsToError = secondsToError;
    }

    public void setPositionInfoService(PositionService positionInfoService) {
        this.positionInfoService = positionInfoService;
    }

    @Override
    public String toString() {
        return "GpsSimulator [id=" + id + ", speedInMps=" + speedInMps + ", shouldMove=" + shouldMove
                + ", exportPositionsToKml=" + exportPositionsToKml + ", exportPositionsToMessaging="
                + exportPositionsToMessaging + ", reportInterval=" + reportInterval + ", currentPosition="
                + positionInfo + "]";
    }

}
