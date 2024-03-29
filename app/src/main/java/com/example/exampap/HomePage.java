package com.example.exampap;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    List<Section> sectionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prepareSections();


        recyclerView = findViewById(R.id.recycler_view);
        adapter = new SectionAdapter(this, sectionList, new OnItemClickListener() {
            @Override
            public void onItemClick(Section section) {
                switch (section.getName()){
                    case "Exams":{
                        Intent intent = new Intent(HomePage.this,ExamsActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        Log.e("$$$$", adapter.getItemCount()+"");
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void prepareSections() {
        int[] draws = new int[]{
                R.drawable.user_profile,
                R.drawable.fees,
                R.drawable.coursework,
                R.drawable.attendance,
                R.drawable.timetable,
                R.drawable.library,
                R.drawable.exams
        };

        Section s = new Section("Profile", draws[0]);
        sectionList.add(s);

        s = new Section("Fees", draws[1]);
        sectionList.add(s);

        s = new Section("Coursework", draws[2]);
        sectionList.add(s);

        s = new Section("Attendance", draws[3]);
        sectionList.add(s);

        s = new Section("Timetable", draws[4]);
        sectionList.add(s);

        s = new Section("Library", draws[5]);
        sectionList.add(s);

        s = new Section("Exams", draws[6]);
        sectionList.add(s);

      //  adapter.notifyDataSetChanged();

    }

    private class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}

