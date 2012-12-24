package com.example.proto001;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity extends Activity implements
		DialogInterface.OnClickListener, OnItemClickListener {

	private ImageView imageView;
	private Gallery gallery;
	private GalleryAdapter galleryAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		gallery = (Gallery) findViewById(R.id.gallery1);
		imageView = (ImageView) findViewById(R.id.imageView1);

		galleryAdapter = new GalleryAdapter(this, imageIds, 200, 200);
		// ギャラリーの画像リストアダプター作成
		gallery.setAdapter(galleryAdapter);

		// gallery.setOnItemClickListener(this);

		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				System.gc();
				imageView.setImageResource((Integer) gallery.getAdapter()
						.getItem(position));
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case DialogInterface.BUTTON_NEUTRAL:
			dialog.dismiss();
			finish();
		}
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (parent == gallery) {
			imageView.setImageResource((int) gallery
					.getItemIdAtPosition(position));
		}

	}

	// @Override
	// protected void onDestroy() {
	//
	// super.onDestroy();
	// System.gc();
	// claenupView(findViewById(R.id.imageView1));
	//
	// }
	//
	// public static final void claenupView(View view) {
	// if (view instanceof ImageView) {
	// ImageView iv = (ImageView) view;
	// iv.setImageDrawable(null);
	// }
	//
	// view.setBackgroundDrawable(null);
	// // view.setBackgroundResource(null);
	//
	// if (view instanceof ViewGroup) {
	// ViewGroup vg = (ViewGroup) view;
	// int size = vg.getChildCount();
	// for (int i = 0; i < size; i++) {
	// claenupView(vg.getChildAt(i));
	// }
	// }
	//
	// }

	private static Integer[] imageIds = {
			// R.drawable.aa0045m,
			// R.drawable.aa0046m,
			// R.drawable.aaa001_16801050,
			// R.drawable.april05,
			// R.drawable.good_cat01,
			// R.drawable.ic_action_search,
			// R.drawable.ic_launcher,
			R.drawable.tn_0001, R.drawable.tn_0002, R.drawable.tn_0003,
			R.drawable.tn_0004, R.drawable.tn_0005, R.drawable.tn_0006,
			R.drawable.tn_0007, R.drawable.tn_0008, R.drawable.tn_0009,
			R.drawable.tn_0010, R.drawable.tn_0011, R.drawable.tn_0012,
			R.drawable.ks_0001, R.drawable.ks_0002, R.drawable.ks_0003,
			R.drawable.ks_0004, R.drawable.ks_0005, R.drawable.ks_0006,
			R.drawable.ks_0007, R.drawable.ks_0008, R.drawable.ks_0009,
			R.drawable.ks_0010, R.drawable.kss_0001, R.drawable.kss_0002,
			R.drawable.kss_0003, R.drawable.kss_0004, R.drawable.kss_0005,
			R.drawable.kss_0006, R.drawable.kss_0007, R.drawable.kss_0008,
			R.drawable.kss_0009, R.drawable.kss_0010, R.drawable.kss_0011,
			R.drawable.kss_0012, R.drawable.kss_0013, R.drawable.kss_0014,
			R.drawable.kss_0015, R.drawable.kss_0016, R.drawable.kss_0017,
			R.drawable.kss_0018, R.drawable.kss_0019, R.drawable.kss_0020,
			R.drawable.kss_0021, R.drawable.kss_0022, R.drawable.kss_0023,
			R.drawable.kss_0024, R.drawable.kss_0025, R.drawable.kss_0026,
			R.drawable.kss_0027, R.drawable.kss_0028, R.drawable.kss_0029,
			R.drawable.kss_0030, };

}
