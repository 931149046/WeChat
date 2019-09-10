package we_chat.service;


import we_chat.pojo.City;

import java.util.List;
import java.util.Map;

public interface CityService {
    List<City> getCity(String pid);

    City findById(String id);
}
