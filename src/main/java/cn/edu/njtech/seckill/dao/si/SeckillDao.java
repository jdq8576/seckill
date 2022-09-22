package cn.edu.njtech.seckill.dao.si;

import cn.edu.njtech.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface SeckillDao {
    int deleteByPrimaryKey(Long seckillId);

    int insert(Seckill record);

    int insertSelective(Seckill record);

    Seckill selectByPrimaryKey(Long seckillId);

    int updateByPrimaryKeySelective(Seckill record);

    int updateByPrimaryKey(Seckill record);

    int reduceNumber(@Param("seckillId") Long seckillId, @Param("killTime") Date killTime);

    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}