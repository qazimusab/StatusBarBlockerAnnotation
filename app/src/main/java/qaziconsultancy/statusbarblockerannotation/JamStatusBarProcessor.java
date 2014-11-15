package qaziconsultancy.statusbarblockerannotation;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by Musab on 11/14/2014.
 */
public class JamStatusBarProcessor {

    public static final String TAG = "statusbar";

    public static void jamStatusBar(Object object) {
        try {
            Log.e(TAG , "1");
            Class target = object.getClass();
            for (Field field : target.getDeclaredFields()) {
                Log.e(TAG , "2");
                try {
                    Log.e(TAG , "3");
                    for (Annotation a : field.getDeclaredAnnotations()) {
                        Log.e(TAG , "4");
                        if (a.annotationType() == JamStatusBar.class) {
                            Log.e(TAG , "5");
                            field.setAccessible(true);
                            Object fieldObject = field.get(object);
                            if (fieldObject != null) {
                                Log.e(TAG , "6");
                                //Do something
                                if(fieldObject instanceof Context){
                                    Log.e(TAG , "7");
                                    Context context = (Context) fieldObject;
                                    WindowManager manager = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));

                                    WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
                                    localLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
                                    localLayoutParams.gravity = Gravity.TOP;

                                    localLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                                            // this is to enable the notification to recieve touch events
                                            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                                            // Draws over status bar
                                            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

                                    localLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                                    localLayoutParams.height = (int) (50 * context.getResources()
                                            .getDisplayMetrics().scaledDensity);
                                    localLayoutParams.format = PixelFormat.TRANSPARENT;

                                    StatusBarTouchInterceptor view = new StatusBarTouchInterceptor(context);

                                    manager.addView(view, localLayoutParams);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}