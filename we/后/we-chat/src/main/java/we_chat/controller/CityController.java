package we_chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import we_chat.pojo.City;
import we_chat.service.CityService;

import java.util.List;

@RequestMapping("/city")
@RestController
public class CityController {
    @Autowired
    public CityService cityService;

    @RequestMapping("/getCity")
    public List<City> getCity() {
        return cityService.getCity("0");
    }

    @RequestMapping("/getScity/{pids}")
    public List<City> getScity(@PathVariable("pids") String pids) {
        return cityService.getCity(pids);
    }

    @RequestMapping("/getReScity/{id}")
    public City getReScity(@PathVariable("id") String id) {
        return cityService.findById(id);
    }

}
