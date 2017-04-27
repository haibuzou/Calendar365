# Calendar365
仿365的日历显示方式
日历部分的实现采用的是aige的DatePicker，感谢aige的日历控件
## 1.0
顶部的悬挂效果采用比较简单的通过位置来判断 week的显示和消失来实现
整体的滑动效果使用ViewDragHelper来实现。
底部的UI变化是通过removeView和addview的方式来进行动态改变。

## 关于日期回调
左右滑动切换日期变化的回调方法
```java
MonthView.OnDateChangeListener
```

```java
@Override
public void onDateChange(int year, int month) {
    // your code  
}
```

点击选择日期的回调
```java
MonthView.OnDatePickedListener
```

```java
@Override
public void onDatePicked(String date) {
    // your code
}
```
返回的日期格式为 yyyy.mm.dd 可以根据需求进行转换


## 运行效果
![](https://github.com/haibuzou/Calendar365/raw/master/art/ScreenShot.gif)
