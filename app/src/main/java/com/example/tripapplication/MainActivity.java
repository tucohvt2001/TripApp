package com.example.tripapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.tripapplication.adapter.RecentsAdapter;
import com.example.tripapplication.adapter.TopPlacesAdapter;
import com.example.tripapplication.databinding.ActivityMainBinding;
import com.example.tripapplication.model.RecentsData;
import com.example.tripapplication.model.TopPlacesData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    SQLiteDatabase db;
    ActivityMainBinding binding;

    private RecyclerView recentRecycler, topPlacesRecycler;
    private RecentsAdapter recentsAdapter;
    private TopPlacesAdapter topPlacesAdapter;
    private ViewFlipper viewFlipper;
    private Animation in, out;
    private List<TopPlacesData> topPlacesDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewFlipper();
        loadData();
        loadTopData();

        showUser();
        binding.imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu();
            }
        });
    }

    private void viewFlipper() {
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        out = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
    }

    private void showMenu() {
        PopupMenu popupMenu = new PopupMenu(this, binding.imgInfo);
        popupMenu.getMenuInflater().inflate(R.menu.menu_user, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuLogout:
                        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
    }

    private void showUser() {
        Intent data = MainActivity.this.getIntent();
        if (data == null) {
            Toast.makeText(MainActivity.this, "no data", Toast.LENGTH_SHORT).show();
        }
        binding.tvShowUser.setText(data.getStringExtra(SignInActivity.KEY_USER));
    }

    private void setRecentRecycler(List<RecentsData> recentsDataList) {
        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);
    }

    private void setTopPlacesRecycler(List<TopPlacesData> topPlacesDataList) {
        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);
    }

    private void loadData() {
        List<RecentsData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentsData("Vịnh Hạ Long", "Quảng Ninh", "Giá : $500 - $700", "Vịnh Hạ Long là một vịnh nhỏ thuộc phần bờ tây vịnh Bắc Bộ tại khu vực biển Đông Bắc Việt Nam.Là trung tâm của một khu vực rộng lớn có những yếu tố ít nhiều tương đồng về địa chất, địa mạo, cảnh quan, khí hậu và văn hóa.", R.drawable.rsz_halong));
        recentsDataList.add(new RecentsData("Cố Đô Hoa Lư", "Ninh Bình", "Giá : $1200 - $1400", "Trải qua hơn 1000 năm chứng kiến biết bao thăng trầm và đổi thay trong lịch sử thế nhưng cho đến nay Hoa Lư vẫn giữ nguyên được vẻ đẹp từ thuở nguyên sơ với những bức tường thành vững chãi phủ kín rêu phong.", R.drawable.recentimage2));
        recentsDataList.add(new RecentsData("Vinpearl Safari Phú Quốc", "Phú Quốc", "Giá : $2000 - $3200", "Tại Vinpearl Resort & Spa Phú Quốc du khách sẽ có được những trải nghiệm ẩm thực vô cùng đa dạng, từ buffet quốc tế đáp ứng nhu cầu mọi thành viên tới fine dining đẳng cấp trong không gian nhà hàng sang trọng.", R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Phong Nha - Kẻ Bàng", "Quảng Bình", "Giá : $1000 - $1400", "Nằm ở miền Trung Việt Nam, tỉnh Quảng Bình, cách thành phố Đồng Hới 50 km về phía Tây Bắc, cách thủ đô Hà Nội khoảng 500 km về phía Nam, Vườn Quốc gia Phong Nha -  Kẻ Bàng được UNESCO vinh danh là Di sản thiên nhiên thế giới. ", R.drawable.recentimage2));
        recentsDataList.add(new RecentsData("Mũi Né - Phan Thiết", "Bình Thuận", "Giá : $800 - $1100", "Du Lịch Mũi Né là một trong những điểm du lịch sinh thái biển đẹp và thơ mộng nhất của tỉnh Bình Thuận. Du khách đến Mũi Né không chỉ bị hấp dẫn bởi vẻ đẹp hoang sơ của tự nhiên mà còn bị thu hút bởi nét ẩm thực độc đáo .", R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Đà Lạt", "Lâm Đồng", "Giá : $2500 - $3000", "Nhắc đến Đà Lạt, chắc hẳn khách du lịch không thể không nhớ đến những tên gọi mỹ miều như “thành phố nên thơ”, “thành phố sương\",.. Trực thuộc tỉnh Lâm Đồng, nơi đây được thiên nhiên ưu ái rất nhiều phong cảnh đẹp cùng không khí trong lành.", R.drawable.recentimage2));

        setRecentRecycler(recentsDataList);
    }

    private void loadTopData() {
        topPlacesDataList.add(new TopPlacesData("SaPa", "Lào Cai", "Giá : $1000", R.drawable.rsz_halong, "Bên cạnh thác nhân tạo, suối mát lạnh cùng không gian xanh trong lành, khu du lịch này còn mang đến nhiều trò chơi dưới nước hấp dẫn, thu hút nhiều bạn trẻ đến khám phá.Phù hợp cho những buổi dã ngoại cùng gia đình và bạn bè trong dịp cuối tuần."));
        topPlacesDataList.add(new TopPlacesData("Cát Bà", "Hải Phòng", "Giá : $1600", R.drawable.rsz_catba, "Bên cạnh thác nhân tạo, suối mát lạnh cùng không gian xanh trong lành, khu du lịch này còn mang đến nhiều trò chơi dưới nước hấp dẫn, thu hút nhiều bạn trẻ đến khám phá.Phù hợp cho những buổi dã ngoại cùng gia đình và bạn bè trong dịp cuối tuần."));
        topPlacesDataList.add(new TopPlacesData("Phố cổ Đồng Văn", "Hà Giang", "Giá : $4200", R.drawable.top1, "Bên cạnh thác nhân tạo, suối mát lạnh cùng không gian xanh trong lành, khu du lịch này còn mang đến nhiều trò chơi dưới nước hấp dẫn, thu hút nhiều bạn trẻ đến khám phá.Phù hợp cho những buổi dã ngoại cùng gia đình và bạn bè trong dịp cuối tuần."));
        topPlacesDataList.add(new TopPlacesData("Tràng An", "Ninh Bình", "Giá : $1234", R.drawable.top2, "Bên cạnh thác nhân tạo, suối mát lạnh cùng không gian xanh trong lành, khu du lịch này còn mang đến nhiều trò chơi dưới nước hấp dẫn, thu hút nhiều bạn trẻ đến khám phá.Phù hợp cho những buổi dã ngoại cùng gia đình và bạn bè trong dịp cuối tuần."));
        topPlacesDataList.add(new TopPlacesData("Biển Sầm Sơn", "Thanh Hóa", "Giá : $8000", R.drawable.top3, "Bên cạnh thác nhân tạo, suối mát lạnh cùng không gian xanh trong lành, khu du lịch này còn mang đến nhiều trò chơi dưới nước hấp dẫn, thu hút nhiều bạn trẻ đến khám phá.Phù hợp cho những buổi dã ngoại cùng gia đình và bạn bè trong dịp cuối tuần."));
        topPlacesDataList.add(new TopPlacesData("Núi Bà Đen", "Tây Ninh", "Giá : $1700 VND", R.drawable.top4, "Bên cạnh thác nhân tạo, suối mát lạnh cùng không gian xanh trong lành, khu du lịch này còn mang đến nhiều trò chơi dưới nước hấp dẫn, thu hút nhiều bạn trẻ đến khám phá.Phù hợp cho những buổi dã ngoại cùng gia đình và bạn bè trong dịp cuối tuần."));
        topPlacesDataList.add(new TopPlacesData("Vịnh Hạ Long", "Quảng Ninh", "Giá : $1102", R.drawable.top5, "Bên cạnh thác nhân tạo, suối mát lạnh cùng không gian xanh trong lành, khu du lịch này còn mang đến nhiều trò chơi dưới nước hấp dẫn, thu hút nhiều bạn trẻ đến khám phá.Phù hợp cho những buổi dã ngoại cùng gia đình và bạn bè trong dịp cuối tuần."));
        topPlacesDataList.add(new TopPlacesData("Bến Ninh Kiều", "Cần Thơ", "Giá : $5000", R.drawable.rsz_halong, "Bên cạnh thác nhân tạo, suối mát lạnh cùng không gian xanh trong lành, khu du lịch này còn mang đến nhiều trò chơi dưới nước hấp dẫn, thu hút nhiều bạn trẻ đến khám phá.Phù hợp cho những buổi dã ngoại cùng gia đình và bạn bè trong dịp cuối tuần."));
        topPlacesDataList.add(new TopPlacesData("Bán đảo Tuần Châu", "Quảng Ninh", "Giá : $3000", R.drawable.rsz_catba, "Bên cạnh thác nhân tạo, suối mát lạnh cùng không gian xanh trong lành, khu du lịch này còn mang đến nhiều trò chơi dưới nước hấp dẫn, thu hút nhiều bạn trẻ đến khám phá.Phù hợp cho những buổi dã ngoại cùng gia đình và bạn bè trong dịp cuối tuần."));
        topPlacesDataList.add(new TopPlacesData("Bãi biển Cửa Lò", "Nghệ An", "Giá : $1600", R.drawable.top1, "Bên cạnh thác nhân tạo, suối mát lạnh cùng không gian xanh trong lành, khu du lịch này còn mang đến nhiều trò chơi dưới nước hấp dẫn, thu hút nhiều bạn trẻ đến khám phá.Phù hợp cho những buổi dã ngoại cùng gia đình và bạn bè trong dịp cuối tuần."));

        setTopPlacesRecycler(topPlacesDataList);
    }
}