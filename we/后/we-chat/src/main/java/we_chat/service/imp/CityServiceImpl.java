package we_chat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import we_chat.dao.CityDao;
import we_chat.pojo.City;
import we_chat.service.CityService;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    public CityDao cityDao;

    @Override
    public List<City> getCity(String pid) {
       return cityDao.getCity(pid);
    }

    @Override
    public City findById(String id) {
        return cityDao.findById(id);
    }
}
