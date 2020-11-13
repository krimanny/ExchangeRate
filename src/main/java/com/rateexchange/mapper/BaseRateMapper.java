package com.rateexchange.mapper;


import com.rateexchange.model.BaseRate;
import org.apache.ibatis.annotations.*;

public interface BaseRateMapper {

    @Select("SELECT * FROM TBL_BASE_RATES WHERE base = #{base} and date_acquired = CURRENT_DATE")
    BaseRate findAllByBase(@Param("base") String base);

    @Select("SELECT * FROM TBL_BASE_RATES WHERE base = #{base} and date_acquired = #{date}")
    BaseRate findAllByBaseAndDate(@Param("base") String base,
                                  @Param("date") String date);

    @Insert({"insert into TBL_BASE_RATES(base, rate_from) values(#{base}, #{rateFrom})"})
    @Options(useGeneratedKeys = true, keyProperty = "baseId", keyColumn = "base_id")
    long insertBaseRate(BaseRate baseRate);
}
