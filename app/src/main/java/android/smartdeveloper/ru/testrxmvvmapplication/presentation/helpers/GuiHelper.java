package android.smartdeveloper.ru.testrxmvvmapplication.presentation.helpers;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class GuiHelper {
    public static void renderImage(ImageView view, String avatarUrl) {
        Picasso.get()
                .load(avatarUrl)
                .fit()
                .centerInside()
                .into(view);
    }
}
