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

    @Test
    public void threeSum() {
        List<List<Integer>> result = threeSumImpl(new int[]{-1, 0, 1, 2, -1, -4});
        result.forEach(item -> System.out.println(item));
    }

    private List<List<Integer>> threeSumImpl(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {

                        boolean exists = false;
                        for (List<Integer> es : resultList) {
                            if (es.get(0) == nums[i]
                                    && es.get(1) == nums[j]
                                    && es.get(2) == nums[k]) {
                                exists = true;
                                break;
                            }
                        }

                        if (!exists) {
                            resultList.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        }
                    }
                }
            }
        }
        return resultList;
    }

    @Test
    public void TestList() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        Assert.assertTrue(list1.contains(list2.get(1)));
    }

    private void printCountryList(List<Country> countryList) {
        for (Country country : countryList) {
            System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }
}
