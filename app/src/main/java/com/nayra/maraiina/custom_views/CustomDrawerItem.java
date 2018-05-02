package com.nayra.maraiina.custom_views;

import android.support.v4.widget.DrawerLayout;

import com.mikepenz.materialdrawer.holder.ColorHolder;
import com.mikepenz.materialdrawer.model.AbstractBadgeableDrawerItem;

import java.util.List;

public class CustomDrawerItem extends AbstractBadgeableDrawerItem<CustomDrawerItem> {

    private ColorHolder background;

    public CustomDrawerItem withBackgroundColor(int backgroundColor) {
        this.background = ColorHolder.fromColor(backgroundColor);
        return this;
    }

    public CustomDrawerItem withBackgroundRes(int backgroundRes) {
        this.background = ColorHolder.fromColorRes(backgroundRes);
        return this;
    }

    @Override
    public CustomDrawerItem withSelectedColor(int selectedColor) {
        this.background = ColorHolder.fromColor(selectedColor);
        return this;
    }

    @Override
    public void bindView(ViewHolder holder, List payloads) {
        super.bindView(holder, payloads);

        if (background != null) {
            background.applyToBackground(holder.itemView);
            DrawerLayout.LayoutParams lp = new DrawerLayout.LayoutParams(holder.itemView.getLayoutParams());
            lp.setMargins(0, 5, 0, 5);
            holder.itemView.setLayoutParams(lp);
        }
    }
}