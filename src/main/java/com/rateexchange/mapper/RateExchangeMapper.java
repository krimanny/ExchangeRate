package com.rateexchange.mapper;

import com.rateexchange.model.Rate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;

@MappedTypes(Rate.class)
public interface RateExchangeMapper {

    @Select("SELECT * FROM TBL_EXCHANGE_RATES WHERE rate_from = #{rateFrom}")
    List<Rate> findByRateFrom(@Param("rateFrom") String rateFrom);

    @Select("SELECT * FROM TBL_EXCHANGE_RATES WHERE base_id = #{baseId}")
    List<Rate> findByBaseId(long baseId);

    @Insert("insert into TBL_EXCHANGE_RATES(rate, currency, base_id) values(#{rate}, #{currency}, #{baseId})")
    void insertRate(double rate, String currency, long baseId);
}
