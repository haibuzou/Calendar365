package com.haibuzou.datepicker.calendar.entities;

/**
 * 日历数据实体
 * 封装日历绘制时需要的数据
 * 
 * Entity of calendar
 *
 * @author AigeStudio 2015-03-26
 */
public class DPInfo {
    private  String strG,strF ;
    private boolean isHoliday;
    private boolean isChoosed;
    private boolean isToday;
    private boolean isWeekend;
    private boolean isSolarTerms, isFestival, isDeferred;
    private boolean isDecorBG;
    private boolean isDecorTL, isDecorT, isDecorTR, isDecorL, isDecorR;

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }

    public boolean isSolarTerms() {
        return isSolarTerms;
    }

    public void setSolarTerms(boolean solarTerms) {
        isSolarTerms = solarTerms;
    }

    public boolean isFestival() {
        return isFestival;
    }

    public void setFestival(boolean festival) {
        isFestival = festival;
    }

    public boolean isDeferred() {
        return isDeferred;
    }

    public void setDeferred(boolean deferred) {
        isDeferred = deferred;
    }

    public boolean isDecorBG() {
        return isDecorBG;
    }

    public void setDecorBG(boolean decorBG) {
        isDecorBG = decorBG;
    }

    public boolean isDecorTL() {
        return isDecorTL;
    }

    public void setDecorTL(boolean decorTL) {
        isDecorTL = decorTL;
    }

    public boolean isDecorT() {
        return isDecorT;
    }

    public void setDecorT(boolean decorT) {
        isDecorT = decorT;
    }

    public boolean isDecorTR() {
        return isDecorTR;
    }

    public void setDecorTR(boolean decorTR) {
        isDecorTR = decorTR;
    }

    public boolean isDecorL() {
        return isDecorL;
    }

    public void setDecorL(boolean decorL) {
        isDecorL = decorL;
    }

    public boolean isDecorR() {
        return isDecorR;
    }

    public void setDecorR(boolean decorR) {
        isDecorR = decorR;
    }


    public boolean isWeekend() {
        return isWeekend;
    }

    public void setWeekend(boolean weekend) {
        isWeekend = weekend;
    }


    public String getStrG() {
        return strG;
    }

    public void setStrG(String strG) {
        this.strG = strG;
    }




}