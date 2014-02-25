package org.geeklub.customview;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.geeklub.utils.ScreenUtil;
import org.geeklub.vass.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class MainBackgroundView extends ImageView{

	private Bitmap back;
	private Bitmap mBitmap;
	private double startX = 0;
	private int screenWidth ;
	private int screenHeight ;
	private Resources resources;


	public MainBackgroundView(Context context, AttributeSet attrs) {
		super(context, attrs);

		resources = context.getResources();

		screenWidth = ScreenUtil.getScreenWidth(context);
		screenHeight = ScreenUtil.getScreenHeight(context);


		back = BitmapFactory.decodeResource(resources, R.drawable.rootblock_default_bg);
		mBitmap = Bitmap.createScaledBitmap(back, screenWidth * 3, screenHeight, true);  





		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {

				super.handleMessage(msg);

				if(msg.what==1){
					if(startX <= -80){
						startX = 0;
					}else{
						startX = startX - 0.09;
					}
				}
				invalidate();
			}
		};


		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				handler.sendEmptyMessage(1);
			}
		}, 0, 10,TimeUnit.SECONDS);

	}


	@Override
	public void onDraw(Canvas canvas)
	{
		Log.i("TAG", "-----onDraw");
		canvas.drawBitmap(mBitmap, (float)startX , 0 , null);
	}



}
