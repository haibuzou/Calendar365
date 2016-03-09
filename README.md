# Calendar365
仿365的日历显示方式
日历部分的实现采用的是aige的DatePicker，感谢aige的日历控件
##1.0
顶部的悬挂效果采用比较简单的通过位置来判断 week的显示和消失来实现
整体的滑动效果使用ViewDragHelper来实现，由于这种类似嵌套的滑动收拾处理，
底部的UI变化是通过addview的方式来进行动态改变，而不是用listview来实现。
因为listview对触摸事件有自己的一套拦截机制。

##运行效果
![](https://github.com/haibuzou/Calendar365/raw/master/art/ScreenShot.gif)