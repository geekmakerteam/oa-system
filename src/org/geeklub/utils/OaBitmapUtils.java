package org.geeklub.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.graphics.Bitmap;

/**
 * 将bitmap转换为Inputstream的工具类
 * @author hp
 *
 */
public class OaBitmapUtils {


	/**
	 * 将bitmap转换为inputstream的方法
	 * @param mPicture-bitmap
	 * @return bitmap转换为inputstream
	 */
	public static InputStream Bitmap2stream(Bitmap mPicture){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		mPicture.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
		return inputStream;

	}

}
