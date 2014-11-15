package qaziconsultancy.statusbarblockerannotation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;


public class DemoApplication extends Activity {

    @JamStatusBar
    Context context;
    WindowManager manager;
    StatusBarTouchInterceptor view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_application);
        context = getBaseContext();
    }

    @Override
    protected void onStart() {
        super.onStart();
        JamStatusBarProcessor.jamStatusBar(this);
    }

    @Override
    protected void onDestroy() {
        if(manager != null && view != null){
            try {
                manager.removeView(view);
            }
            catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if(manager != null && view != null){
            try {
                manager.removeView(view);
            }
            catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }
        super.onPause();
    }
}
