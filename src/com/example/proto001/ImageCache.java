package com.example.proto001;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.util.Log;

public class ImageCache {
	/**
     * 
     */
	private static HashMap<Integer, SoftReference<Bitmap>> cache = new HashMap<Integer, SoftReference<Bitmap>>();

	/**
	 * @param key
	 * @return
	 */
	public static Bitmap getImageView(Integer key) {
		if (cache.containsKey(key)) {
			SoftReference<Bitmap> reference = cache.get(key);
			if (reference.get() != null) {

				return reference.get();
			}
		}
		return null;
	}

	/**
	 * @param key
	 * @param imageview
	 */
	public static void setImageView(Integer key, Bitmap imageview) {


		cache.put(key, new SoftReference<Bitmap>(imageview));
	}

	/**
	 * @param key
	 * @return
	 */
	public static boolean hasImageView(Integer key) {
		return cache.containsKey(key);
	}

	/**
     * 
     */
	public static void clear() {
		cache.clear();
	}
}
