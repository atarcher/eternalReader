package com.gbh.eternalreader.vm.rack;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.gbh.eternalreader.database.entity.Book;
import com.gbh.eternalreader.ui.fragment.BookRackFragment;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * Created by gbh
 * Date 2021/1/18
 * Description
 */
public class BookGridItemViewModel extends BookItemViewModel{

    public BookGridItemViewModel(@NonNull BookRackViewModel viewModel) {
        super(viewModel);
    }

    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("短暂点击");
        }
    });

    public BindingCommand itemLongClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("长按反馈");
        }
    });
}
