package com.nayra.maraiina.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.CustomDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Drawer result;

    private CustomDrawerItem[] drawerItems = new CustomDrawerItem[6];

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();
        initMenu();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initMenu() {

        final CustomDrawerItem item1 = initMenuItem(R.string.drawer_item_home, R.color.green_selected_menu, 1);

        final CustomDrawerItem item2 = initMenuItem(R.string.drawer_item_previous_orders, R.color.green_very_dark, 2);

        final CustomDrawerItem item3 = initMenuItem(R.string.drawer_item_new_products, R.color.green_very_dark, 3);

        final CustomDrawerItem item4 = initMenuItem(R.string.drawer_item_opinions_notifications, R.color.green_very_dark, 4);

        final CustomDrawerItem item5 = initMenuItem(R.string.drawer_item_contact_us, R.color.green_very_dark, 5);

        final CustomDrawerItem item6 = initMenuItem(R.string.drawer_item_about_us, R.color.green_very_dark, 6);

        drawerItems[0] = item1;
        drawerItems[1] = item2;
        drawerItems[2] = item3;
        drawerItems[3] = item4;
        drawerItems[4] = item5;
        drawerItems[5] = item6;

        //create the drawer and remember the `Drawer` result object
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHeader(R.layout.header_menu)
                .withSliderBackgroundColor(ContextCompat.getColor(this, R.color.green_light))
                .withHeaderDivider(true)
                .withDisplayBelowStatusBar(true)
                .addDrawerItems(
                        drawerItems
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        for (int i = 0; i < 6; i++) {
                            if (i == (position - 1)) {
                                drawerItems[i] = setItemSelected(drawerItems[i]);
                                result.updateItemAtPosition(drawerItems[i], i);
                            } else {
                                drawerItems[i] = setItemNotSelected(drawerItems[i]);
                                result.updateItemAtPosition(drawerItems[i], i);
                            }
                        }

                        Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_LONG).show();
                        return true;
                    }
                })
                .build();
    }

    private CustomDrawerItem initMenuItem(int name, int color, int id) {
        final CustomDrawerItem item = new CustomDrawerItem()
                .withIdentifier(id)
                .withBackgroundColor(getResources().getColor(color))
                .withName(name)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.WHITE);

        return item;
    }

    private CustomDrawerItem setItemNotSelected(CustomDrawerItem item) {
        item.withBackgroundColor(R.color.green_very_dark);

        return item;
    }

    private CustomDrawerItem setItemSelected(CustomDrawerItem item) {
        item.withBackgroundColor(R.color.green_selected_menu);

        return item;
    }
}
