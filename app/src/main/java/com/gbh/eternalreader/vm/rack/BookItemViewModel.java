package com.gbh.eternalreader.vm.rack;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableInt;

import com.bumptech.glide.Glide;
import com.gbh.eternalreader.R;
import com.jakewharton.rxbinding2.view.RxView;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

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
    public ObservableInt imgVisibility = new ObservableInt();

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

    /**
     * view的onLongClick事件绑定
     */
    @SuppressLint("CheckResult")
    @BindingAdapter(value = {"onLongClickCommand"}, requireAll = false)
    public static void onLongClickCommand(View view, final BindingCommand clickCommand) {
        RxView.longClicks(view)
                .subscribe(object -> {
                    if (clickCommand != null) {
                        clickCommand.execute();
                    }
                });
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
