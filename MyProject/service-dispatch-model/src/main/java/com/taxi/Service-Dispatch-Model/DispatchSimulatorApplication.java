package com.taxi.DispatchSimulator;

import com.taxi.DispatchSimulator.map.DriverMap;
import com.taxi.DispatchSimulator.map.MapSettings;
import com.taxi.DispatchSimulator.object.Driver;
import com.taxi.DispatchSimulator.object.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.taxi.DispatchSimulator.bussiness.StartDispatch.start;

@SpringBootApplication
public class DispatchSimulatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(DispatchSimulatorApplication.class, args);

		System.out.println("================Simulator Printing================");

	}
}
