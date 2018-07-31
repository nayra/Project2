package com.nayra.maraiina.views;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.nayra.maraiina.Constants;
import com.nayra.maraiina.MyApplication;
import com.nayra.maraiina.R;
import com.nayra.maraiina.custom_views.CustomDrawerItem;
import com.nayra.maraiina.model.OrderDetailsModel;
import com.nayra.maraiina.util.FragmentUtils;
import com.nayra.maraiina.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /*@BindView(R.id.imgBtnToolbar)
    ImageButton menuImageButton;

    @BindView(R.id.tvToolbarTitle)
    MyBoldTextView toolbar_title;*/

    private Drawer result;
    private CustomDrawerItem[] drawerItems = new CustomDrawerItem[6];
    private CustomDrawerItem lastSelectedItem;
    private int REQUEST_CODE = 200;


    HomeFragment fragment = new HomeFragment();
    //private int open = 1;

    private ArrayList<OrderDetailsModel> myOrders = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Utils.setCallerClass(this);
        //open = getIntent().getIntExtra("menu", 1);

        initToolbar();
        initMenu();

        navigateToMenuItem();
        //Utils.displayNextActivity(this , OrderDetailsActivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && requestCode == REQUEST_CODE) {
            myOrders = data.getParcelableArrayListExtra(Constants.ORDERS_LIST);
            fragment.setOrders(myOrders);
        } else if (requestCode == REQUEST_CODE) {
            myOrders = new ArrayList<>();
            fragment.setOrders(myOrders);
        }
    }

    private void navigateToMenuItem() {
        Intent intent = getIntent();
        int item_num = intent.getIntExtra(Constants.MENU_ITEM_NUMBER, -1);

        if (item_num != -1) {
            setItemSelected(drawerItems[item_num - 1]);
            result.updateItem(drawerItems[item_num - 1]);

            lastSelectedItem = setItemNotSelected(lastSelectedItem);
            result.updateItem(lastSelectedItem);

            lastSelectedItem = drawerItems[item_num - 1];
            menuNavigation(item_num);
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.icon);
        toolbar.setTitle("");
        toolbar.setNavigationOnClickListener(view -> {
            if (result.isDrawerOpen())
                result.closeDrawer();
            else
                result.openDrawer();
        });
    }

    private void initMenu() {

        initAllMenuItems();

        result = new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.header_menu)
                .withSliderBackgroundColor(ContextCompat.getColor(this, R.color.green_very_dark))
                .withHeaderDivider(true)
                .withDisplayBelowStatusBar(true)
                .withDrawerGravity(Gravity.START)
                .addDrawerItems(
                        drawerItems
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem != lastSelectedItem) {
                            drawerItem = setItemSelected((CustomDrawerItem) drawerItem);
                            result.updateItem(drawerItem);

                            lastSelectedItem = setItemNotSelected(lastSelectedItem);
                            result.updateItem(lastSelectedItem);

                            lastSelectedItem = (CustomDrawerItem) drawerItem;

                            menuNavigation(position);
                        }
                        result.closeDrawer();
                        return true;
                    }
                })
                .build();


    }

    public void menuNavigation(int pos) {
        switch (pos) {
            case 1:
                fragment.setOrders(myOrders);
                FragmentUtils.replaceFragment(R.id.frame_container, this, fragment, HomeFragment.class.getSimpleName());
                break;

            case 2:
                OffersFragment offersFragment = new OffersFragment();
                FragmentUtils.replaceFragment(R.id.frame_container, this, offersFragment, OffersFragment.class.getSimpleName());
                break;

            case 3:
                SuggestionsFragment suggestionsFragment = new SuggestionsFragment();
                FragmentUtils.replaceFragment(R.id.frame_container, this, suggestionsFragment, SuggestionsFragment.class.getSimpleName());
                break;

            case 4:
                AboutUsFragment aboutUsFragment = new AboutUsFragment();
                FragmentUtils.replaceFragment(R.id.frame_container, this, aboutUsFragment, AboutUsFragment.class.getSimpleName());
                break;
            case 5:
                ContactUsFragment contactUsFragment = new ContactUsFragment();
                FragmentUtils.replaceFragment(R.id.frame_container, this, contactUsFragment, ContactUsFragment.class.getSimpleName());
                break;
            /*case 6:
                AboutApplicationFragment aboutApplicationFragment = new AboutApplicationFragment();
                FragmentUtils.replaceFragment(R.id.frame_container, this, aboutApplicationFragment, AboutApplicationFragment.class.getSimpleName());
                break;*/
            default:
                HomeFragment defaultFragment = new HomeFragment();
                FragmentUtils.replaceFragment(R.id.frame_container, this, defaultFragment, HomeFragment.class.getSimpleName());
                break;

        }

    }

    private void initAllMenuItems() {
        final CustomDrawerItem item1 = initMenuItem(R.string.drawer_item_home, R.color.green_selected_menu, 1);

        final CustomDrawerItem item2 = initMenuItem(R.string.drawer_item_offers, R.color.green_very_dark, 2);

        final CustomDrawerItem item3 = initMenuItem(R.string.drawer_item_complains_and_suggestions, R.color.green_very_dark, 3);

        final CustomDrawerItem item4 = initMenuItem(R.string.drawer_item_about, R.color.green_very_dark, 4);

        final CustomDrawerItem item5 = initMenuItem(R.string.drawer_item_contact, R.color.green_very_dark, 5);

        // final CustomDrawerItem item6 = initMenuItem(R.string.drawer_item_about_application, R.color.green_very_dark, 6);

        drawerItems[0] = item1;
        drawerItems[1] = item2;
        drawerItems[2] = item3;
        drawerItems[3] = item4;
        drawerItems[4] = item5;
        //drawerItems[5] = item6;

        lastSelectedItem = item1;

        menuNavigation(1);
    }

    private CustomDrawerItem initMenuItem(int name, int color, int id) {
        Typeface menuTypeFace = MyApplication.getmInstance().getTypeFace(Constants.KUFI_REGULAR);

        final CustomDrawerItem item = new CustomDrawerItem()
                .withIdentifier(id)
                .withBackgroundColor(getResources().getColor(color))
                .withName(name)
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.WHITE)
                .withTypeface(menuTypeFace);

        return item;
    }

    private CustomDrawerItem setItemNotSelected(CustomDrawerItem item) {
        item.withBackgroundColor(getResources().getColor(R.color.green_very_dark));

        return item;
    }

    private CustomDrawerItem setItemSelected(CustomDrawerItem item) {
        item.withBackgroundColor(getResources().getColor(R.color.green_selected_menu));
        item.withSetSelected(true);
        return item;
    }

    @Override
    public void onFragmentInteraction(int catId, int subId, String type, String img_url) {
        Intent intent = new Intent(this, OrderDetailsActivity.class);
        intent.putExtra(Constants.CATEGORY_ID, catId);
        intent.putExtra(Constants.SUBCATEGORY_ID, subId);
        intent.putExtra(Constants.TYPE_NAME, type);
        intent.putExtra(Constants.CATEGORY_IMAGE, img_url);
        if (myOrders == null)
            myOrders = new ArrayList<>();
        intent.putParcelableArrayListExtra(Constants.ORDERS_LIST, myOrders);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onBackFromMyOrders(ArrayList<OrderDetailsModel> models) {
        myOrders = models;
    }

    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        Utils.displayNextActivityFinish(this, ChooseCountryActivity.class);
    }
}
