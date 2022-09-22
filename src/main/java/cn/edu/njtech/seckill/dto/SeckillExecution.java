package cn.edu.njtech.seckill.dto;

import cn.edu.njtech.seckill.entity.SuccessKilled;
import cn.edu.njtech.seckill.enums.SeckillStatEnum;


public class SeckillExecution {
    private long seckillId;

    private int state;

    private String stateinfo;

    private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, SeckillStatEnum statEnum) {
        super();
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateinfo = statEnum.getStateInfo();
    }

    public SeckillExecution(long seckillId, SeckillStatEnum statEnum, SuccessKilled successKilled) {
        super();
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateinfo = statEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateinfo() {
        return stateinfo;
    }

    public void setStateinfo(String stateinfo) {
        this.stateinfo = stateinfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

}
