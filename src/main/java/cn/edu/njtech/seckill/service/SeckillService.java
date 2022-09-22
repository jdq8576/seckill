package cn.edu.njtech.seckill.service;


import cn.edu.njtech.seckill.dto.Exposer;
import cn.edu.njtech.seckill.dto.SeckillExecution;
import cn.edu.njtech.seckill.entity.Seckill;
import cn.edu.njtech.seckill.exception.RepeatKillException;
import cn.edu.njtech.seckill.exception.SeckillCloseException;
import cn.edu.njtech.seckill.exception.SeckillException;

import java.util.List;

public interface SeckillService {
    /**
     * 获取秒杀列表
     *
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 获取某一条商品秒杀信息
     *
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 暴露url
     *
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     *
     * @param seckill
     * @param userPhone
     * @param md5
     */
    SeckillExecution excuteSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, SeckillCloseException, RepeatKillException;
}