// Load Map 
// var map = L.map('map').setView([40.061, -97.515], 4);
var mapboxUrl = 'https://{s}.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={token}';
var mapID = 'albertoaflores.llpjgl43';
var mapToken = 'pk.eyJ1IjoiYWxiZXJ0b2FmbG9yZXMiLCJhIjoiS3duWUxzUSJ9.X1rRTTRkktNR7DFIc0DsCw';
var mapboxAttribution = 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>';

var MESSAGE_STATUS_TO_STATUS_MAP = {
    'NONE': 'None',
    'SERVICE_INFO': 'ServiceInfo',
    'SERVICE_SOON': 'ServiceSoon',
    'SERVICE_NOW': 'ServiceNow',
    'STOP_NOW': 'StopTruck'
};

var serviceMapData = {
    'ServiceInfo': {distance: 10000, color: 'blue'},
    'ServiceSoon': {distance: 3000, color: 'yellow'},
    'ServiceNow': {distance: 1000, color: 'orange'},
    'StopTruck': {distance: 500, color: 'red'},
};

L.FlashingMarker = L.Marker.extend({
    options: {
        interval: 500,
    },

    onAdd: function (map) {
        L.Marker.prototype.onAdd.call(this, map);
        this.start();
    },

    onRemove: function (map) {
        this.stop();
        L.Marker.prototype.onRemove.call(this, map);
    },

    flash: function () {
        if (this._icon) {
            if (this._flashed) {
                this._icon.src = this.options.icon.options.iconUrl;
                delete this._flashed;
            } else {
                this._icon.src = this.options.icon.options.flashIconUrl;
                this._flashed = true;
            }

            // Queue up the animation
            if (this.options.icon.options.flashIconUrl && this.options.interval > 0) {
                this._tid = setTimeout(this.flash.bind(this), this.options.interval);
            } else {
                this.stop();
            }
        }
    },

    setIcon: function (icon) {
        L.Marker.prototype.setIcon.call(this, icon);
        if (icon.options.flashIconUrl) {
            if (this._icon && this._flashed) {
                this._icon.src = icon.options.flashIconUrl;
            }
            this.start();
        } else {
            this.stop();
        }
    },

    // Start the animation
    start: function () {
        if (!this._started && this.options.icon.options.flashIconUrl && this.options.interval && this.options.interval > 0) {
            delete this._flashed;
            this.flash();
            this._started = true;
        }
    },

    // Stop the animation in place
    stop: function () {
        if (this._tid) {
            clearTimeout(this._tid);
            delete this._tid;
        }
        if (this._flashed && this._icon) {
            this._icon.src = this.options.icon.options.iconUrl;
            delete this._flashed;
        }
        this._started = false;
    }

});

L.flashingMarker = function (latlngs, options) {
    return new L.FlashingMarker(latlngs, options);
};

// create map
var map = L.map('map', {
    center: [38.9047, -77.0164],
    zoom: 13,
//	markerZoomAnimation: false
});

var miniMap = L.map('miniMap', {
    center: [38.9047, -77.0164],
    zoom: 15
});

// Creates markers for each group
var inMotionNormalMarker = icon('car', '#009500', true);
var stoppedNormalMarker = icon('car', '#009500');
var inMotionServiceInfoMarker = icon('car', '#0000FF', true);
var stoppedServiceInfoMarker = icon('car', '#0000FF');
var inMotionServiceSoonMarker = icon('car', '#FFFF00', true);
var stoppedServiceSoonMarker = icon('car', '#FFFF00');
var inMotionServiceNowMarker = icon('car', '#FFA500', true);
var stoppedServiceNowMarker = icon('car', '#FFA500');
var inMotionStopNowMarker = icon('car', '#FF0000', true);
var stoppedStopNowMarker = icon('car', '#FF0000');

// Create markers for the Spring taxi Unit Locations
//var serviceCenterMarker = icon('commercial', '#5F9EA0');
var serviceCenterMarker = L.icon({
    iconUrl: 'images/wrench' + (L.Browser.retina ? '@2x' : '') + '.png',
    iconSize: [24, 24],
    iconAnchor: [12, 12],
    popupAnchor: [0, -12]
});
var nearestServiceCenter = L.icon({
    iconUrl: 'images/wrench' + (L.Browser.retina ? '@2x' : '') + '.png',
    iconSize: [24, 24],
    iconAnchor: [12, 12],
    popupAnchor: [0, -12],
    className: 'faa-flash animated'
});

function icon(symbol, color, flash, size) {
    size = size || [35, 90];
    color = (color || '7e7e7e').replace('#', '');
    var icon = L.icon({
        iconUrl: 'http://a.tiles.mapbox.com/v4/marker/pin-l' + (symbol ? "-" + symbol : '') + '+' + color + (L.Browser.retina ? '@2x' : '') + '.png?access_token=' + mapToken,
        iconSize: [35, 90],
        iconAnchor: [size[0] / 2, size[1] / 2],
        popupAnchor: [0, -size[1] / 2],
        flashIconUrl: flash ? 'http://a.tiles.mapbox.com/v4/marker/pin-l+' + color + (L.Browser.retina ? '@2x' : '') + '.png?access_token=' + mapToken : undefined
    });
    return icon;
}

function setupDefaultMap() {
    // add map tiles
    L.tileLayer(mapboxUrl, {
        attribution: mapboxAttribution,
        maxZoom: 18,
        subdomains: ['a', 'b', 'c', 'd'],
        id: mapID,
        token: mapToken
    }).addTo(map);

    // query for all points by default
    initFilter();
    initVehicles();
    setupWebSocketConnection();
}

function setupMinimap() {
    // add map tiles
    L.tileLayer(mapboxUrl, {
        maxZoom: 18,
        subdomains: ['a', 'b', 'c', 'd'],
        id: mapID,
        token: mapToken
    }).addTo(miniMap);
}

// configure search bar widget
var visualSearch;
function setupSearchBar() {
    // initialize search box
    $(document).ready(function () {
        visualSearch = VS.init({
            container: $('.visual_search'),
            query: '',
            showFacets: true,
            unquotable: [],
            callbacks: {
                search: function (query, searchCollection) {
                    // perform query
                    updateSearch();
                },
                facetMatches: function (callback) {
                    callback(['Customer', 'VIN', 'Unit ID']);
                },
                valueMatches: function (facet, searchTerm, callback) {

                }
            }
        });
    });
}

// empty query object
function buildQuery() {
    var queryFilter = {};
    queryFilter.vins = [];
    queryFilter.customers = [];
    queryFilter.unitIds = [];
    queryFilter.serviceFilters = [];
    queryFilter.vehicleMovementFilters = [];
    return queryFilter;
}

function initFilter() {
    filter = buildQuery();
}

function setupWebSocketConnection() {
    $.ajax({
//		beforeSend: function(xhrObj){
//			xhrObj.setRequestHeader("Content-Type","application/json");
//	    },
        url: 'clientConfig',
        success: function (stompUrl) {
            if (stompUrl) {
                var socket = new SockJS("//" + stompUrl);
                var stompClient = Stomp.over(socket);
                stompClient.heartbeat.outgoing = 0;
                stompClient.heartbeat.incoming = 0;
                stompClient.debug = function () {
                };  //turn off debugging
                var on_connect = function () {
                    console.log('Connected to vehicle updates: ' + stompUrl);
                    stompClient.subscribe("/topic/vehicles", function (m) {
                        var updateMsg = JSON.parse(m.body);
                        handleUpdateMessage(updateMsg);
                    });
                };
                var on_error = function () {
                    /*
                     * Re-attempt to connect to web socket.
                     */
                    setTimeout(setupWebSocketConnection, 3000);
                };
                stompClient.connect('guest', 'guest', on_connect, on_error, '/');
            } else {
                console.error('Unknown URL for "service-location-updater". Vehicle location updates are disabled!');
            }
        },
        error: function (xhr, error) {
            console.error('Cannot retrieve "service-location-updater" URL.');
            console.error(JSON.stringify(error));
            /*
             * Re-attempt to setup a web socket connection.
             */
            setTimeout(setupWebSocketConnection, 3000);
        },
//	    dataType: 'json'
    });
}

function initVehicles() {
    var vehicles = [];
    // processing - spin!
    $("#spinnerIcon").show();
    collectPages('/fleet-location-service/locations', function (result) {
        vehicles = vehicles.concat(result.locations);
    }, function () {
        vehiclesIndex = {};

//		var trimedData = [];
//		for (var i = 0; i < vehicles.length && trimedData.length < 1; i++) {
//			if (vehicles[i].vin && vehicles[i].latitude && vehicles[i].longitude) {
//				trimedData.push(vehicles[i]);
//			}
//		}
//		vehicles = trimedData;

        // Fix up the data
        vehicles.forEach(function (vehicle) {
            if (vehicle.vin) {
                vehiclesIndex[vehicle.vin] = vehicle;
            }
        });

        showVehicles();

        $("#spinnerIcon").hide();
    });
}

function showVehicles() {
    clearFleetMarkers();
    // See https://github.com/Leaflet/Leaflet.markercluster
    markers = /* L.markerClusterGroup({maxClusterRadius: 30}); */ L.layerGroup();
    markersMap = [];
    Object.keys(vehiclesIndex).forEach(function (vin) {
        var vehicle = vehiclesIndex[vin];
        if (shouldShowMarker(vehicle)) {
            if (vehicle.latitude && vehicle.longitude) {
                createMarker(vehicle);
//				setTimeout(function() {
////					if (vehicle.vehicleMovementType === 'IN_MOTION') {
//						liveGpsUpdate(vehiclesIndex[vin]);
////					}
//					liveServiceUpdate(vehiclesIndex[vin]);
//				}, 5000);
            } else {
                var obj = {
                    lat: vehicle.latitude,
                    lon: vehicle.longitude
                };
                msg = "FAIL - VIN: " + vehicle.vin + " JSON: "
                    + JSON.stringify(obj) + " TSP: " + vehicle.tspProvider;
                console.log(msg);
            }
        }
    });
    map.addLayer(markers);
}

function shouldShowMarker(vehicle) {
    return vehicle &&
        (filter.serviceFilters.length === 0 || filter.serviceFilters.indexOf(vehicle.serviceType) >= 0) &&
        (filter.vehicleMovementFilters.length === 0 || filter.vehicleMovementFilters.indexOf(vehicle.vehicleMovementType) >= 0) &&
        (filter.vins.length === 0 || filter.vins.indexOf(vehicle.vin) >= 0) &&
        (!vehicle.unitInfo || filter.customers.length === 0 || filter.customers.indexOf(vehicle.unitInfo.customerName) >= 0) &&
        (!vehicle.unitInfo || filter.unitIds.length === 0 || filter.unitIds.indexOf(vehicle.unitInfo.unitNumber) >= 0);
}

function createMarker(vehicle) {
    var iconType = resolveMarker(vehicle);
    var marker = L.flashingMarker({
        lat: vehicle.latitude,
        lon: vehicle.longitude
    }, {
        icon: iconType
    });
    marker.vin = "" + vehicle.vin;
    marker.vehicle = vehicle;
    marker.on('click', markerClickHandler);
    marker.bindPopup("Vin: " + vehicle.vin);
    markers.addLayer(marker);
    markersMap[vehicle.vin] = marker;
    return marker;
}

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min)) + min;
}

function handleUpdateMessage(msg) {
    var vehicle = vehiclesIndex[msg.vin];
    if (vehicle) {
        var miniMapReZoom = false;
        if (msg.location) {
            vehicle.latitude = msg.location.latitude;
            vehicle.longitude = msg.location.longitude;
        }
        if (msg.vehicleStatus && MESSAGE_STATUS_TO_STATUS_MAP[msg.vehicleStatus]) {
            miniMapReZoom = vehicle.serviceType !== MESSAGE_STATUS_TO_STATUS_MAP[msg.vehicleStatus];
            vehicle.serviceType = MESSAGE_STATUS_TO_STATUS_MAP[msg.vehicleStatus];
        }
        if (msg.heading) {
            vehicle.heading = msg.heading;
        }
        if (msg.speed) {
            vehicle.gpsSpeed = msg.speed;
            vehicle.vehicleMovementType = 'IN_MOTION';
        } else {
            vehicle.gpsSpeed = 0;
            vehicle.vehicleMovementType = 'STOPPED';
        }
        if (msg.serviceLocation) {
            miniMapReZoom = miniMapReZoom || (vehicle.serviceLocation && (msg.serviceLocation.latitude !== vehicle.serviceLocation.latitude || msg.serviceLocation.longitude !== vehicle.serviceLocation.longitude));
            vehicle.serviceLocation = msg.serviceLocation;
        }
        vehicle.timestamp = new Date().toUTCString();
        updateVehicleOnMap(vehicle);
        updateVehicleOnMiniMap(vehicle, miniMapReZoom);
    } else {
        console.log('Cannot update vehicle with VIN: ' + msg.vin);
    }
}

function updateVehicleGpsData(info) {
    var vehicle = vehiclesIndex[info.vin];
    if (vehicle) {
        vehicle.latitude = info.latitude;
        vehicle.longitude = info.longitude;
        vehicle.heading = info.heading;
        vehicle.address = info.address
        vehicle.odometer = info.odometer;
        vehicle.vehicleMovementType = info.movementType;
        updateVehicleOnMap(vehicle);
        updateVehicleOnMiniMap(vehicle);
    }
}

function updateServiceData(info) {
    var vehicle = vehiclesIndex[info.vin];
    if (vehicle) {
        vehicle.serviceType = info.serviceType;
        updateVehicleOnMap(vehicle);
        updateVehicleOnMiniMap(vehicle);
    }
}

function updateVehicleOnMap(vehicle) {
    var marker = markersMap[vehicle.vin];
    if (shouldShowMarker(vehicle)) {
        if (marker) {
            updateMarker(marker);
        } else {
            createMarker(vehicle);
        }
    } else {
        if (marker) {
            removeMarker(marker);
        } else {
            // Do nothing
        }
    }
}

function updateVehicleOnMiniMap(vehicle, reZoom) {
    if (minimapMarker && minimapMarker.vehicle && vehicle.vin === minimapMarker.vehicle.vin) {
        minimapMarker.vehicle = vehicle;
        setupMiniMapMarker(reZoom);
    }
}

function updateMarker(marker) {
    marker.setLatLng({
        lat: marker.vehicle.latitude,
        lon: marker.vehicle.longitude
    });
    marker.setIcon(resolveMarker(marker.vehicle));
//	var isPopupOpened = marker._popup && marker._popup._isOpen;
//	removeMarker(marker);
//	var newMarker = createMarker(marker.vehicle);
//	if (isPopupOpened) {
//		newMarker.openPopup();
//	}
}

function removeMarker(marker) {
    markers.removeLayer(marker);
    delete markersMap[marker.vehicle.vin];
}

function setupMiniMapMarker(reZoom) {
    var vehicle = minimapMarker.vehicle;

    minimapMarker.setLatLng({
        lat: vehicle.latitude,
        lon: vehicle.longitude
    });

    minimapMarker.setIcon(resolveMarker(vehicle));

    if (vehicle.serviceLocation) {
        if (!nearestServiceCenterMarker) {
            nearestServiceCenterMarker = L.marker({
                lat: vehicle.serviceLocation.latitude,
                lon: vehicle.serviceLocation.longitude
            }, {
                icon: nearestServiceCenter
            });
            miniMap.addLayer(nearestServiceCenterMarker);
        }

        nearestServiceCenterMarker.bindPopup(createServiceCenterPopup(vehicle.serviceLocation));
        nearestServiceCenterMarker.setLatLng({
            lat: vehicle.serviceLocation.latitude,
            lon: vehicle.serviceLocation.longitude
        });
    } else {
        if (nearestServiceCenterMarker) {
            miniMap.removeLayer(nearestServiceCenterMarker);
            nearestServiceCenterMarker = null;
        }
    }

    if (reZoom) {
        if (vehicle.serviceLocation) {
            miniMap.fitBounds(L.latLngBounds([[vehicle.latitude, vehicle.longitude], [vehicle.serviceLocation.latitude, vehicle.serviceLocation.longitude]]), {
                animate: true,
                duration: 0.5,
                padding: [15, 15]
            });
        } else {
            miniMap.setView({
                lat: vehicle.latitude,
                lon: vehicle.longitude
            }, 15, {
                animate: true,
                duration: 0.5
            });
        }
    }

    // add circle
    if (!vehicle.serviceType || !serviceMapData[vehicle.serviceType]) {
        if (circle) {
            miniMap.removeLayer(circle);
            circle = null;
        }
    } else {
        var circleData = serviceMapData[vehicle.serviceType];

        if (!circle) {
            circle = L.circle({
                lat: vehicle.latitude,
                lon: vehicle.longitude
            }, 10000);
            circle.addTo(miniMap);
        }

        circle.setLatLng({
            lat: vehicle.latitude,
            lon: vehicle.longitude
        });

        circle.setStyle({
            color: circleData.color,
            fillColor: circleData.color,
            fillOpacity: 0.3
        });

        circle.setRadius(circleData.distance);
    }

    // Loading truck telemetry data
    $('#telemetryVin').text(vehicle.vin);
    $('#telemetryLatitude').text(vehicle.latitude);
    $('#telemetryLongitude').text(vehicle.longitude);
    $('#telemetryAddress').text(vehicle.address);
    $('#telemetryHeading').text(vehicle.heading);
    $('#telemetryOdometer').text(vehicle.odometer);
    $('#telemetryGpsSpeed').text(vehicle.gpsSpeed);
    $('#telemetryGpsStatus').text(vehicle.gpsStatus);
    $('#telemetryTotalIdleTime').text(vehicle.totalIdleTime);
    $('#telemetryTotalFuelUsage').text(vehicle.totalFuelUsage);
    $('#telemetryTimestamp').text(vehicle.timestamp);
    $('#telemetryTotalEngineTime').text(vehicle.totalEngineTime);
    $('#telemetryTspProvider').text(vehicle.tspProvider);

    // update Spring Taxi unit info
    if (vehicle.unitInfo) {
        $('#rentmeUnitInfo').show();
        $("#unitNumber").text(vehicle.unitInfo.unitNumber);
        $("#customerName").text(vehicle.unitInfo.customerName);
        $("#engineMake").text(vehicle.unitInfo.engineMake);
    } else {
        $('#rentmeUnitInfo').hide();
    }

    // update fault info
    if (vehicle.unitFault) {
        $('#rentmeFaultInfo').show();
        $("#faultSpn").text(vehicle.unitFault.spn);
        $("#faultFmi").text(vehicle.unitFault.fmi);

        // update fault code info
        if (vehicle.faultCode) {
            $('.rentmeFaultCode').show();
            $("#rentmeFCfaultCode").text(vehicle.faultCode.faultCode);
            $("#rentmeFCfaultCodeId").text(
                vehicle.faultCode.faultCodeId);
            $("#rentmeFCdescription").text(
                vehicle.faultCode.description);
            $("#rentmeFCinstructions").text(
                vehicle.faultCode.repairInstructions);
        } else {
            $('.rentmeFaultCode').hide();
        }
    } else {
        $('#rentmeFaultInfo').hide();
    }
}

function simulateMove(vehicle) {
    return {
        vin: vehicle.vin,
        latitude: vehicle.latitude + getRandomInt(-1000, 1000) * 0.00001,
        longitude: vehicle.longitude + getRandomInt(-1000, 1000) * 0.00001,
        heading: vehicle.heading,
        address: vehicle.address,
        odometer: getRandomInt(100, 599999),
        movementType: getRandomInt(0, 100) < 50 ? 'STOPPED' : 'IN_MOTION'
    };
}

function liveGpsUpdate(vehicle) {
    updateVehicleGpsData(simulateMove(vehicle));
    setTimeout(function () {
        liveGpsUpdate(vehicle);
    }, 3000);
}

function liveServiceUpdate(vehicle) {
    var serviceInfoVals = ['None', 'ServiceInfo', 'ServiceSoon', 'ServiceNow', 'StopTruck'];
    updateServiceData({
        vin: vehicle.vin,
        serviceType: serviceInfoVals[getRandomInt(0, serviceInfoVals.length - 1)]
    });
    setTimeout(function () {
        liveServiceUpdate(vehicle);
    }, 5000);
}

function resolveMarker(vehicle) {
    var movementType = vehicle.vehicleMovementType;
    var serviceType = vehicle.serviceType;
    var iconType = stoppedNormalMarker;
    if (movementType == 'IN_MOTION' && serviceType == 'None') {
        iconType = inMotionNormalMarker;
    } else if (movementType == 'STOPPED' && serviceType == 'None') {
        iconType = stoppedNormalMarker;
    } else if (movementType == 'IN_MOTION' && serviceType == 'ServiceInfo') {
        iconType = inMotionServiceInfoMarker;
    } else if (movementType == 'STOPPED' && serviceType == 'ServiceInfo') {
        iconType = stoppedServiceInfoMarker;
    } else if (movementType == 'IN_MOTION' && serviceType == 'ServiceSoon') {
        iconType = inMotionServiceSoonMarker;
    } else if (movementType == 'STOPPED' && serviceType == 'ServiceSoon') {
        iconType = stoppedServiceSoonMarker;
    } else if (movementType == 'IN_MOTION' && serviceType == 'ServiceNow') {
        iconType = inMotionServiceNowMarker;
    } else if (movementType == 'STOPPED' && serviceType == 'ServiceNow') {
        iconType = stoppedServiceNowMarker;
    } else if (movementType == 'IN_MOTION' && serviceType == 'StopTruck') {
        iconType = inMotionStopNowMarker;
    } else if (movementType == 'STOPPED' && serviceType == 'StopTruck') {
        iconType = stoppedStopNowMarker;
    }

    return iconType;
}

var markers;
var markersMap;
var vehiclesIndex = {};
var sidebar;
var minimapMarker;
var nearestServiceCenterMarker;
var circle;
var filter;
function markerClickHandler(event) {
    if (!(sidebar.isVisible())) {
        sidebar.toggle();
    }

    // update miniMap
    if (minimapMarker) {
        miniMap.removeLayer(minimapMarker);
        minimapMarker = null;
    }

    if (circle) {
        miniMap.removeLayer(circle);
        circle = null;
    }

//	map.panTo({
//		lat : this.vehicle.latitude,
//		lon : this.vehicle.longitude
//	}, {
//		duration : 0.5
//	});

    // add marker
    var iconType = resolveMarker(this.vehicle);
    minimapMarker = L.flashingMarker({
        lat: this.vehicle.latitude,
        lon: this.vehicle.longitude
    }, {
        icon: iconType
    });
    minimapMarker.vehicle = this.vehicle;
    minimapMarker.addTo(miniMap);

    setupMiniMapMarker(true);
}

//function closeTruckInfoView() {
//	$('#vehicleView').popup('hide');
//}

// This runs from the "Search Bar" and from the "Filter" pop-up
function updateSearch() {
    filter = buildQuery();

    // iterate over the facets
    $.each(visualSearch.searchQuery.facets(), function (index, value) {
        if (value['VIN']) {
            filter.vins.push(value['VIN']);
        }
        if (value['Customer']) {
            filter.customers.push(value['Customer']);
        }
        if (value['Unit ID']) {
            filter.unitIds.push(value['Unit ID']);
        }
    });

    $('input[name="serviceFilter"]:checked').each(function () {
        filter.serviceFilters.push(this.value);
    });
    $('input[name="vehicleMovementFilter"]:checked').each(function () {
        filter.vehicleMovementFilters.push(this.value);
    });

    $("#spinnerIcon").show();
    showVehicles();
    $("#spinnerIcon").hide();
}

function clearFleetMarkers() {
    // clean up map
    if (markers) {
        map.removeLayer(markers);
        markers = null;
    }
    // clear the id -> markers map
    markersMap = [];
}

/**
 * Utility to create an unique ID using coordinates. This is needed so that we
 * can access it from other places in the DOM.
 */
function createIdFromCoordinates(latitude, longitude) {
    var messageId = "" + latitude + longitude;
    messageId = messageId.replace(/\./g, '');
    messageId = messageId.replace(/-/g, '');
    return messageId;
}

function setupServiceCenters() {
    var scg = new L.LayerGroup();
    var scg2 = new L.LayerGroup();
    var data = [];

    collectPages(
        '/service-location-service/serviceLocations',
        function (result) {
            data = data.concat(result.locations);
        },
        function () {
            // iterate over the list of results
            data
                .forEach(function (value, index) {

                    var content = createServiceCenterPopup(value);

                    L.marker({
                        lat: value.latitude,
                        lon: value.longitude
                    }, {
                        icon: serviceCenterMarker
                    }).bindPopup(content).addTo(scg);

                    L.marker({
                        lat: value.latitude,
                        lon: value.longitude
                    }, {
                        icon: serviceCenterMarker
                    }).bindPopup(content).addTo(scg2);

                });
        });

    // Overlay layers are grouped
    var groupedOverlays = {
        "<i class='fa fa-car'></i> Service Center Locations": {
            "Service Center <i class='fa fa-wrench'></i>": scg
        }
    };

    // Overlay layers are grouped
    var groupedOverlays2 = {
        "<i class='fa fa-car'></i> Service Center Locations": {
            "Service Center <i class='fa fa-wrench'></i>": scg2
        }
    };

    // Use the custom grouped layer control, not "L.control.layers"
    var layerControl = L.control.groupedLayers(null, groupedOverlays, null);
    map.addControl(layerControl);
    var layerControl2 = L.control.groupedLayers(null, groupedOverlays2, null);
    miniMap.addControl(layerControl2);
}

function createServiceCenterPopup(data) {
    return "<div class='scg_popup'>"
        + "<div class='loc_header'><i class='fa fa-wrench'></i>Spring Taxi Service Center</div>"
        + (typeof data.address2 === 'string' && data.address2.length > 0 ? "<div class='loc_comments'>" + data.address2 + "</div>" : '')
        + "<div>" + data.address1 + "</div>" + "<div>"
        + data.city + ", " + data.state + " "
        + data.zip + "</div>" + "</div>";
}

function getMiles(i) {
    return i * 0.000621371192;
}

function getMeters(i) {
    return i * 1609.344;
}

function setupMapLegend() {
    // control that shows state info on hover
    var info = L.control({
        position: 'bottomleft'
    });

    info.onAdd = function (map) {
        this._div = L.DomUtil.create('div', 'info');
        var img = '<img src="/images/car-18@2x.png" height="18" width="18">';
        this._div.innerHTML = '<h4 style="color: black">Spring Taxi Vehicles</h4>'
            + '<i class="faa-flash animated">' + img + '</i> Moving &nbsp;&nbsp;&nbsp;&nbsp; ' + img + ' Stopped <br/>'
            + '<i class="fa fa-map-marker" style="color: green;"></i> Normal &nbsp;&nbsp;'
            + '<i class="fa fa-map-marker" style="color: blue"></i> ServiceInfo &nbsp;&nbsp;'
            + '<i class="fa fa-map-marker" style="color: yellow"></i> ServiceSoon &nbsp;&nbsp;'
            + '<i class="fa fa-map-marker" style="color: orange"></i> ServiceNow &nbsp;&nbsp;'
            + '<i class="fa fa-map-marker" style="color: red"></i> StopNow &nbsp;';
        return this._div;
    };

    info.addTo(map);
}

function setupSidebar() {
    sidebar = L.control.sidebar('sidebar', {
        closeButton: true,
        position: 'right'
    });
    map.addControl(sidebar);

    map.on('click', function () {
        sidebar.hide();
    });

    sidebar.on('show', function () {
        console.log('Sidebar will be visible.');
    });

    sidebar.on('shown', function () {
        console.log('Sidebar is visible.');
    });

    sidebar.on('hide', function () {
        console.log('Sidebar will be hidden.');
    });

    sidebar.on('hidden', function () {
        console.log('Sidebar is hidden.');
    });

    L.DomEvent.on(sidebar.getCloseButton(), 'click', function () {
        console.log('Close button clicked.');
    });
}

// See http://dev.vast.com/jquery-popup-overlay/  
$(document).ready(function () {
    // Initialize the plugin
    $('#my_popup').popup({
        type: 'tooltip',
        transition: '0.3s all 0.1s',
        tooltipanchor: $('#navbar'),
        vertical: 'bottom',
        onopen: function () {
            $('input[name="serviceFilter"]').each(function () {
                this.checked = filter.serviceFilters.indexOf(this.value) >= 0;
            });
            $('input[name="vehicleMovementFilter"]').each(function () {
                this.checked = filter.vehicleMovementFilters.indexOf(this.value) >= 0;
            });
        }
    });
});

setupSearchBar();
setupDefaultMap();
setupMinimap();
setupServiceCenters();
setupMapLegend();
setupSidebar();
