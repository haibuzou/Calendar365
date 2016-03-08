package com.haibuzou.datepicker.calendar.bizs.themes;

/**
 * 主题的默认实现类
 * 
 * The default implement of theme
 *
 * @author AigeStudio 2015-06-17
 */
public class DPBaseTheme extends DPTheme {
    @Override
    public int colorBG() {
        return 0xFFFFFFFF;
    }

    @Override
    public int colorBGCircle() {
        return 0x904882d8;
    }

    @Override
    public int colorTitleBG() {
        return 0xFFF37B7A;
    }

    @Override
    public int colorTitle() {
        return 0xEEFFFFFF;
    }

    @Override
    public int colorToday() {
        return 0xEEFF733A;
    }

    @Override
    public int colorG() {
        return 0xEE666666;
    }

    @Override
    public int colorF() {
        return 0xEEC08AA4;
    }

    @Override
    public int colorWeekend() {
        return 0xEEF78082;
    }

    @Override
    public int colorHoliday() {
        return 0x80FED6D6;
    }

	@Override
	public int colorTodayText() {
		return 0xEEFF733A;
	}

	@Override
	public int colorChooseText() {
		return 0x00000000;
	}
}
