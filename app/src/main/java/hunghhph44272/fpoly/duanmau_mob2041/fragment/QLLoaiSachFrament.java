package hunghhph44272.fpoly.duanmau_mob2041.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hunghhph44272.fpoly.duanmau_mob2041.DAO.LoaiSachDAO;
import hunghhph44272.fpoly.duanmau_mob2041.Model.LoaiSach;
import hunghhph44272.fpoly.duanmau_mob2041.Model.item_click;
import hunghhph44272.fpoly.duanmau_mob2041.R;
import hunghhph44272.fpoly.duanmau_mob2041.adapter.loaisachAdapter;

public class QLLoaiSachFrament extends Fragment {
    RecyclerView recyclerLoaiSach;
    EditText edtloaisach;
    int maloai;
    LoaiSachDAO dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_qlloaisach,container,false);
        recyclerLoaiSach = view.findViewById(R.id.recyclerLoaisach);
        edtloaisach = view.findViewById(R.id.edtloaisach);
        Button btnthem = view.findViewById(R.id.btnThem);
        Button btnsua = view.findViewById(R.id.btnSua);
        dao = new LoaiSachDAO(getContext());

        loadData();

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = edtloaisach.getText().toString();
                if (dao.themLoaiSach(tenloai)){
                    //thông báo + load lại danh sách
                    loadData();
                    edtloaisach.setText("");
                }else {
                    Toast.makeText(getContext(), "Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = edtloaisach.getText().toString();
                LoaiSach loaiSach = new LoaiSach(maloai,tenloai);
                if (dao.suaLoaiSach(loaiSach)){
                    loadData();
                    edtloaisach.setText("");
                }else {
                    Toast.makeText(getContext(), "Thay đổi thông tin thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiSach.setLayoutManager(linearLayoutManager);
        ArrayList<LoaiSach> list = dao.getDSloaiSach();
        loaisachAdapter adapter = new loaisachAdapter(getContext(), list, new item_click() {
            @Override
            public void onClickLs(LoaiSach loaiSach) {
                edtloaisach.setText(loaiSach.getTenLoai());
                maloai = loaiSach.getMaLoai();
            }
        });
        recyclerLoaiSach.setAdapter(adapter);
    }

}
