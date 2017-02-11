package com.brianneldon.ilovezappos;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.brianneldon.ilovezappos.databinding.ProductFragmentBinding;
import com.squareup.picasso.Picasso;

/**
 * Created by Brian on 2/2/2017.
 */

public class ProductActivity extends ActionBarActivity {

    ProductCollectionPagerAdapter productCollectionPagerAdapter;
    ProductViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.product_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Results");

        viewPager = (ProductViewPager) findViewById(R.id.product_pager);
        productCollectionPagerAdapter = new ProductCollectionPagerAdapter(getSupportFragmentManager(), this);
        viewPager.storeAdapter(productCollectionPagerAdapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.product_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("", "Add to Cart pressed");
                ProductManager.incCartCount();
                Button button = (Button) findViewById(R.id.product_checkout_button);
                button.setText("Check Out (" + ProductManager.getCartCount() + ")");
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadeout);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Button plus1 = (Button) findViewById(R.id.plus_one);
                        plus1.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Button plus1 = (Button) findViewById(R.id.plus_one);
                        plus1.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                Button plus1 = (Button) findViewById(R.id.plus_one);
                plus1.startAnimation(animation);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ProductCollectionPagerAdapter extends FragmentStatePagerAdapter {
        FragmentActivity activity;
        public ProductCollectionPagerAdapter(FragmentManager fm, FragmentActivity activity) {
            super(fm);
            this.activity = activity;
        }

        @Override
        public Fragment getItem(int i) {
            Log.d("", "Making new ProductFragment");
            ProductManager.setCurrentProduct(i);
            Fragment fragment = new ProductFragment();

            return fragment;
        }

        @Override
        public int getCount() {
            return ProductManager.getCurrentResultCount();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Product " + (position + 1);
        }
    }

    public static class ProductFragment extends Fragment {
        public static final String ARG_OBJECT = "object";

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

            Product product = ProductManager.getCurrentProduct();

            //data binding and inflate
            ProductFragmentBinding binding = DataBindingUtil.inflate(
                    inflater, R.layout.product_fragment, container, false);
            View view = binding.getRoot();
            binding.setProduct(product);

            return view;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            Product product = ProductManager.getCurrentProduct();
            ImageView imgView = (ImageView) view.findViewById(R.id.product_thumbnail);
            Picasso.with(getActivity().getApplicationContext())
                    .load(product.getThumbnailImageUrl())
                    .into(imgView);
        }
    }

    public void openBrowser(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ProductManager.getCurrentProduct().getProductUrl()));
        startActivity(browserIntent);
    }
}
