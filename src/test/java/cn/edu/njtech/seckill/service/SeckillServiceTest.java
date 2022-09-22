package cn.edu.njtech.seckill.service;

import cn.edu.njtech.seckill.dto.Exposer;
import cn.edu.njtech.seckill.dto.SeckillExecution;
import cn.edu.njtech.seckill.entity.Seckill;
import cn.edu.njtech.seckill.exception.RepeatKillException;
import cn.edu.njtech.seckill.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Y.bear
 * @version 创建时间：2018年9月19日 下午4:32:05 类说明
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillServiceTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() {
        List<Seckill> seckillList = seckillService.getSeckillList();
        for (Seckill seckill : seckillList) {
            logger.info("seckill= {}", seckill);
        }
    }

    @Test
    public void testGetById() {
        long seckillId = 1000;
        Seckill seckill = seckillService.getById(seckillId);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void testExportSeckillUrl() {
        long seckillId = 1002;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long userPhone = 15828224726l;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.excuteSeckill(seckillId, userPhone, md5);
                logger.info("Result:{}", seckillExecution);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn("exposer={}", exposer);
        }
    }
}
