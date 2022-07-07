package com.taxi.DispatchSimulator;

import com.taxi.DispatchSimulator.map.DriverMap;
import com.taxi.DispatchSimulator.map.MapSettings;
import com.taxi.DispatchSimulator.object.Driver;
import com.taxi.DispatchSimulator.object.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.taxi.DispatchSimulator.bussiness.StartDispatch.start;

@Controller
@RequestMapping(path="/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(method = RequestMethod.GET, path="/add")
    public @ResponseBody String addNewOrder () {

        /**
         @RequestParam String orderStartT,
         @RequestParam String orderEndT,
         @RequestParam String orderStartL,
         @RequestParam String orderEndL,
         @RequestParam String driverID,
         @RequestParam String userID,
         @RequestParam String manhattanDistance
         * **/

        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        // ?orderStartT=1&orderEndT=10&orderStartL=a&orderEndL=b&driverID=fd23rr23432e2&userID=jiahui&manhattanDistance=666

        MapSettings settingMap = new MapSettings();
        /** Map area: 100.11 * 10.5 **/
        settingMap.setup(104.00, 104.11, 30.1, 31.0, 0.001);

        DriverMap driverMap = new DriverMap(2000);
        Map<String, Set<Driver>> map = driverMap.getDriverMap();
        Set<Driver> drivers = driverMap.getDriverList();
        List<Order> orderList = start(drivers, map);

        for (Order o : orderList) {
            orderRepository.save(o);
        }
        
        return "Saved";
    }

    @RequestMapping(method = RequestMethod.GET, path="/all")
    public @ResponseBody Iterable<Order> getAllOrders() {
        // This returns a JSON or XML with the users
        return orderRepository.findAll();
    }

}
