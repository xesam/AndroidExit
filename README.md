# Android Exit

参考整理补充：[ Android退出应用最优雅的方式(改进版)](http://blog.csdn.net/soul_code/article/details/50453934)

我们主张遵循 Android 的交互规范，不需要主动关闭 APP。
这里涉及的操作主要是为了应对从 App 深层次退出的需求。

演示代码 [https://github.com/xesam/AndroidExit/blob/master/app/src/main/java/dev/xesam/android/exit/Sub2Activity.java](https://github.com/xesam/AndroidExit/blob/master/app/src/main/java/dev/xesam/android/exit/Sub2Activity.java)

## System.exit()

不推荐。应用可能会自动重启，或者提示错误，视系统而定。

## Activity 集合管理

使用一个 List<WeakReference<Activity>> 来保存所有打开的 Activity，退出的时候关闭所有的 Activity 即可。

第一种实现：

在基类 BaseActivity 的 onCreate 回调里，将 Activity 加入集合，在 onDestroy 回调里移除 Activity。

第二种实现：

4.0 之后，可以使用 ActivityLifecycleCallbacks ,这种方式侵入性更小。

```java

public class StackApp extends Application {
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onCreate() {
        super.onCreate();
        initStack();
    }

    private static List<WeakReference<Activity>> activityStack = new ArrayList<>();

    private void initStack() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return;
        }
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activityStack.add(new WeakReference<>(activity));
            }
            
            ...
            
            @Override
            public void onActivityDestroyed(Activity activity) {
                WeakReference<Activity> target = null;
                for (int size = activityStack.size(), i = size - 1; i >= 0; i--) {
                    WeakReference<Activity> weakReference = activityStack.get(i);
                    if (weakReference.get() == activity) {
                        target = weakReference;
                        break;
                    }
                }
                activityStack.remove(target);
            }
        });
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).get().finish();
            }
        }
        activityStack.clear();
    }
}

```

## 广播

与前面一种方法类似，只不过通过广播让系统来帮我们找到对应的 Activity。

## onNewIntent

根 Activity 使用 singleTask 模式让系统提我们清除所有的 Activity ，然后在关闭最后的 Activity 即可。

```java
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int exitCode = intent.getIntExtra(AppExit.EXTRA_EXIT, 0);
        if (exitCode == 1) {
            finish();
        }
    }
```

问题：

如果根 Activity 是应用的 LAUNCHER Activity（即通过 Launcher 图标启动的），那么这种方式就不可用，因为每次从 Launcher 启动，也会把其他的 Activity 清除。
不过通常我们的应用都会有启动页面，所以根 Activity 不会是 LAUNCHER Activity，所以只要分清除，就不会有问题。



