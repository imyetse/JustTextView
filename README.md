# JustTextView
重写textview，实现较高性能的对齐TextView，与android原生textview一样的用法，兼容所有属性。
性能比原生TextView略差，主要原因是原生TextView除了Spannable、replacement、emoji，基本都是整行绘制，
而对齐需要逐字绘制，具体实现可以看TextLine类。

## 直接使用
xml文件
```

<cn.tseeey.justtext.JustTextView
        android:id="@+id/text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:justify="true"
        android:text="@string/test_string"/>
            
```
代码
```
textview.setText()
```
属性
```
新增属性 justify
默认为true，不想对齐可以设置为false
```
 
 效果图
 ![效果图](https://github.com/imyetse/JustTextView/blob/master/img/img.jpg)


欢迎start、fork
有问题欢迎issue
