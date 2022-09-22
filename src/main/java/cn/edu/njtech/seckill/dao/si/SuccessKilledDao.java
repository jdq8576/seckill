package cn.edu.njtech.seckill.dao.si;

import cn.edu.njtech.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SuccessKilledDao {
    int deleteByPrimaryKey(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);

    int insert(SuccessKilled record);

    int insertSelective(SuccessKilled record);

    SuccessKilled selectByPrimaryKey(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);

    int updateByPrimaryKeySelective(SuccessKilled record);

    int updateByPrimaryKey(SuccessKilled record);

    SuccessKilled queryByIdWithSeckill(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);

    int insertSuccessKilled(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
}