package jack.mybatis.simple.mapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import jack.mybatis.simple.model.Country;

public class CountryMapperTest extends BaseMapperTest {

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = this.getSqlSession();
        try {
            List<Country> countryList = sqlSession.selectList("jack.mybatis.simple.mapper.CountryMapper.selectAll");
            printCountryList(countryList);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUseInterface() {
        try (SqlSession sqlSession = this.getSqlSession()) {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            List<Country> countryList = mapper.selectAll();
            printCountryList(countryList);
        }
    }

    private void printCountryList(List<Country> countryList) {
        for (Country country : countryList) {
            System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }
}
