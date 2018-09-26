package jack.mybatis.simple.mapper;

import java.util.List;
import jack.mybatis.simple.model.Country;

public interface CountryMapper {
    List<Country> selectAll();
}
