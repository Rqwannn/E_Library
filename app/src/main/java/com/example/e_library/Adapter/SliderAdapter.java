package com.example.e_library.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.e_library.Login;
import com.example.e_library.R;
import com.example.e_library.Response.BukuModel;
import com.example.e_library.Welcome.Welcome;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    Context context;

    public SliderAdapter(Context ctx) {
        this.context = ctx;
    }

    int images[] = {
            R.drawable.welcome_icon,
            R.drawable.welcome_icon,
    };

    int headings[] = {
            R.string.heading_one,
            R.string.heading_two
    };

    int description[] = {
            R.string.desc_one,
            R.string.desc_two,
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.welcome_slider_layout,container,false);

        ImageView slidetitleimage = view.findViewById(R.id.gambar_welcome_slider);
        TextView slideHeading = view.findViewById(R.id.title);
        TextView slideDesciption = view.findViewById(R.id.secondary_title);
        MaterialButton btnSelanjutnya = view.findViewById(R.id.btn_selanjutnya);

        slidetitleimage.setImageResource(images[position]);
        slideHeading.setText(headings[position]);
        slideDesciption.setText(description[position]);

        if (position == 1){
            btnSelanjutnya.setVisibility(View.VISIBLE);

            btnSelanjutnya.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Intent = new Intent(context, Login.class);
                    context.startActivity(Intent);
                    ((Activity)context).overridePendingTransition(R.anim.enter_bottom_to_top, R.anim.stay_position);
                    ((Activity)context).finish();
                }
            });

        } else {
            btnSelanjutnya.setVisibility(View.GONE);
        }

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((LinearLayout)object);

    }
}
