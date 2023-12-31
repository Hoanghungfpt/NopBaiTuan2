package hunghhph44272.fpoly.duanmau_mob2041;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import hunghhph44272.fpoly.duanmau_mob2041.DAO.DemoDB;
import hunghhph44272.fpoly.duanmau_mob2041.DAO.ThuThuDAO;
import hunghhph44272.fpoly.duanmau_mob2041.fragment.QLLoaiSachFrament;
import hunghhph44272.fpoly.duanmau_mob2041.fragment.QLSachFragment;
import hunghhph44272.fpoly.duanmau_mob2041.fragment.QLphieumuonFragment;
import hunghhph44272.fpoly.duanmau_mob2041.fragment.QlThanhVienFragment;
import hunghhph44272.fpoly.duanmau_mob2041.fragment.ThongKeDoanhThuFragment;
import hunghhph44272.fpoly.duanmau_mob2041.fragment.thongKeTop10Fragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolBar = findViewById(R.id.toolbar);
        FrameLayout framelayout = findViewById(R.id.frameLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        View headerLayout = navigationView.getHeaderView(0);
        TextView tvTennguoidung = headerLayout.findViewById(R.id.tvNameNguoiDung);

        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                int id = item.getItemId();

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (id == R.id.nav_PhieuMuon) {
                    replaceFragment(new QLphieumuonFragment());
                } else if (id == R.id.nav_LoaiSach) {
                    replaceFragment(new QLLoaiSachFrament());
                } else if (id == R.id.nav_DangXuat) {
                    Intent intent = new Intent(MainActivity.this, Dangnhap.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else if (id == R.id.nav_DoiMatKhau) {
                    showDialogDoiMK();
                }else if (id == R.id.nav_TopMuon) {
                    replaceFragment(new thongKeTop10Fragment());
                }else if (id == R.id.nav_DoanhThu) {
                    replaceFragment(new ThongKeDoanhThuFragment());
                }else if (id == R.id.nav_ThanhVien) {
                    replaceFragment(new QlThanhVienFragment());
                }else if (id == R.id.nav_Sach) {
                    replaceFragment(new QLSachFragment());
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                toolBar.setTitle(item.getTitle());

                return true;
            }
        });

        //Hiển thị chức năng cho ADMIN
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
        String loaiTK = sharedPreferences.getString("loaitaikhoan","");
        if (!loaiTK.equals("ADMIN")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_DoanhThu).setVisible(false);
            menu.findItem(R.id.nav_TopMuon).setVisible(false);
        }

        String hoten = sharedPreferences.getString("hoTen","");
        tvTennguoidung.setText("WELCOM \n "+hoten);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    private void showDialogDoiMK() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setNegativeButton("Cập nhật", null)
                .setPositiveButton("Hủy", null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimk, null);

        EditText edtPassOld = view.findViewById(R.id.edtPassOld);
        EditText edtNewPass = view.findViewById(R.id.edtNewPass);
        EditText edtReNewPass = view.findViewById(R.id.edtReNewPass);
        builder.setView(view);


        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpass = edtPassOld.getText().toString();
                String newpass = edtNewPass.getText().toString();
                String renewpass = edtReNewPass.getText().toString();
                if (oldpass.equals("") || newpass.equals("") || renewpass.equals("")) {
                    Toast.makeText(MainActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (newpass.equals(renewpass)) {
                        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                        String matt = sharedPreferences.getString("maTT", "");

                        ThuThuDAO thuThuDAO = new ThuThuDAO(MainActivity.this);
                        int check = thuThuDAO.capNhatMK(matt, oldpass, newpass);
                        if (check == 1) {
                            Toast.makeText(MainActivity.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Dangnhap.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else if (check == 0) {
                            Toast.makeText(MainActivity.this, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "Mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}