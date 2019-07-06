package com.wq.animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.wq.allandroid.R;
import com.wq.animation.property.BasePropertyActivity;
import com.wq.animation.tween.AlphaActivity;
import com.wq.animation.tween.AnimationSetActivity;
import com.wq.animation.tween.AnimationUtilActivity;
import com.wq.animation.tween.RotateActivity;
import com.wq.animation.tween.ScaleActivity;
import com.wq.animation.tween.TranslateActivity;

/**
 * Animation 公共属性放在AnimationSetActivity
 * 
 * @author Administrator
 *
 */
public class StartAnimationActivity extends Activity implements OnItemClickListener {

	private ListView mListView;
	private String[] mAllItem = { "TranslateAnimation", "ScaleAnimation", "AlphaAnimation", "RotateAnimation",
			"AnimationSet", "AnimationUtil", "PropertyAnimation" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_animation);
		mListView = (ListView) findViewById(R.id.listview);
		mListView.setAdapter(new MyAdapter());
		mListView.setOnItemClickListener(this);
	}

	class MyAdapter extends BaseAdapter {

		public int getCount() {
			// TODO Auto-generated method stub
			return mAllItem.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mAllItem[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Button btn;
			if (convertView == null) {
				convertView = LayoutInflater.from(StartAnimationActivity.this).inflate(R.layout.list_item, null);
				btn = (Button) convertView.findViewById(R.id.btn);
				convertView.setTag(btn);
			} else {
				btn = (Button) convertView.getTag();
			}
			btn.setText(mAllItem[position]);
			return convertView;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			Intent intent0 = new Intent(StartAnimationActivity.this, TranslateActivity.class);
			startActivity(intent0);
			break;
		case 1:
			Intent intent1 = new Intent(StartAnimationActivity.this, ScaleActivity.class);
			startActivity(intent1);
			break;
		case 2:
			Intent intent2 = new Intent(StartAnimationActivity.this, AlphaActivity.class);
			startActivity(intent2);
			break;
			
		case 3:
			Intent intent3 = new Intent(StartAnimationActivity.this, RotateActivity.class);
			startActivity(intent3);
			break;
			
		case 4:
			Intent intent4 = new Intent(StartAnimationActivity.this, AnimationSetActivity.class);
			startActivity(intent4);
			break;
			
		case 5:
			Intent intent5 = new Intent(StartAnimationActivity.this, AnimationUtilActivity.class);
			startActivity(intent5);
			break;
			
		case 6:
			Intent intent6 = new Intent(StartAnimationActivity.this, BasePropertyActivity.class);
			startActivity(intent6);
			break;
		}
	}

}
