package com.example.proto001;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class GalleryAdapter extends BaseAdapter {

	/**
	 * 元画像と反射エフェクト間の距離。
	 */
	private static int REFLECTION_GAP = 4;

	/**
	 * ビットマップの読み込み設定。
	 */
	private BitmapFactory.Options mOptions = new BitmapFactory.Options();

	/**
	 * コンテキスト。
	 */
	private Context context;

	/**
	 * サムネイル画像を動的に生成することを示す値。
	 */
	private boolean mIsMakeThumbnail = true;

	/**
	 * 反射エフェクトを使用することを示す値。
	 */
	private boolean mIsUserEffect = false;

	/**
	 * レイアウト情報。
	 */
	private Gallery.LayoutParams mLayoutParams;

	private Integer[] imageIds;

	public GalleryAdapter(Context context, Integer[] resource, int width,
			int height) {
		this.context = context;
		this.imageIds = resource;
		this.mLayoutParams = new Gallery.LayoutParams(width, height);
	}

	public int getCount() {
		return imageIds.length;
	}

	public long getItemId(int position) {
		return imageIds[position];
	}

	public Object getItem(int position) {
		// return imageItems.get(position);
		return imageIds[position];
	}

	public View getView(int position, View view, ViewGroup parent) {

		ImageView imageView;
		Bitmap bitmap = null;

		bitmap = ImageCache.getImageView(position);

		if (bitmap == null) {
			bitmap = decodeBitmap(this.imageIds[position]);
			ImageCache.setImageView(position, bitmap);

		}

		imageView = new ImageView(context);

		imageView.setLayoutParams(this.mLayoutParams);
//		imageView.setScaleType(ScaleType.CENTER_INSIDE);
		 imageView.setScaleType(ScaleType.FIT_CENTER);
		// imageView.setScaleType(ScaleType.FIT_XY);
		imageView.setImageBitmap(this.mIsUserEffect ? this.makeReflectedImage(
				bitmap, REFLECTION_GAP) : bitmap);

		BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
		drawable.setAntiAlias(true);

		return imageView;
	}

	/**
	 * リソース ID から画像を取得します。
	 * 
	 * @param id
	 *            リソース ID。
	 * 
	 * @return 成功時は Bitmap インスタンス。それ以外は null 参照。
	 */
	private Bitmap decodeBitmap(int id) {
		if (this.mIsMakeThumbnail) {
			this.mOptions.inJustDecodeBounds = true;
			BitmapFactory.decodeResource(this.context.getResources(), id,
					this.mOptions);

			int width = (this.mOptions.outWidth / this.mLayoutParams.width) + 1;
			int height = (this.mOptions.outHeight / this.mLayoutParams.height) + 1;
			int scale = Math.max(width, height);

			this.mOptions.inJustDecodeBounds = false;
			this.mOptions.inSampleSize = scale;

			return BitmapFactory.decodeResource(this.context.getResources(),
					id, this.mOptions);

		} else {
			return BitmapFactory
					.decodeResource(this.context.getResources(), id);
		}
	}

	/**
	 * 画像の下部に反射エフェクトを付けた Bitmap を生成します。
	 * 
	 * @param src
	 *            元となる画像。
	 * @param gap
	 *            元画像と反射エフェクト間の距離。
	 * 
	 * @return 成功時は Bitmap インスタンス。それ以外は null 参照。
	 */
	private Bitmap makeReflectedImage(Bitmap src, int gap) {
		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		int width = src.getWidth();
		int height = src.getHeight();
		int destHeight = height + height / 2;
		Bitmap effect = Bitmap.createBitmap(src, 0, height / 2, width,
				height / 2, matrix, false);
		// Bitmap dest = Bitmap.createBitmap( width, destHeight,
		// Config.ARGB_4444 );
		Bitmap dest = Bitmap.createBitmap(width, destHeight, Config.ARGB_8888);
		Canvas canvas = new Canvas(dest);

		canvas.drawBitmap(src, 0, 0, null);
		canvas.drawRect(0, height, width, height + gap, new Paint());
		canvas.drawBitmap(effect, 0, height + gap, null);

		Paint paint = new Paint();
		paint.setShader(new LinearGradient(0, height, 0, destHeight + gap,
				0x70ffffff, 0x00ffffff, TileMode.CLAMP));
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		canvas.drawRect(0, height, width, destHeight + gap, paint);

		return dest;
	}
}
