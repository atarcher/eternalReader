package com.gbh.eternalreader.vm.rack;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.gbh.eternalreader.R;
import com.gbh.eternalreader.database.entity.Book;
import com.gbh.eternalreader.ui.fragment.BookRackFragment;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * Created by gbh
 * Date 2021/1/14
 * Description
 */
public class BookItemViewModel<VM extends BaseViewModel> extends ItemViewModel<VM> {

    public BookItemViewModel(@NonNull VM viewModel) {
        super(viewModel);
    }

    protected Object itemType;

    public Object getItemType() {
        return itemType;
    }

    public void setItemType(Object itemType) {
        this.itemType = itemType;
    }

    @BindingAdapter({"onClickCommand"})
    public static void onClickCommand(LinearLayout layout, final BindingCommand onClick) {
        layout.setOnClickListener(v -> {
            if (onClick != null) {
                onClick.execute();
            }
        });
    }

    @BindingAdapter({"itemClick"})
    public static void itemClick(RelativeLayout relativeLayout, final BindingCommand itemClick) {
        relativeLayout.setOnClickListener(v -> {
            if (itemClick != null) {
                itemClick.execute();
            }
        });
    }

    @BindingAdapter({"url"})
    public static void setImageUri(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .error(R.drawable.no_cover)
                    .placeholder(R.drawable.no_cover)
                    .into(imageView);
        }
    }

    /* @BindingAdapter({"onLongClickCommand"})
    public static void onLongClickCommand(RelativeLayout relativeLayout, final BindingCommand itemLongClick) {
        relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }*/
}