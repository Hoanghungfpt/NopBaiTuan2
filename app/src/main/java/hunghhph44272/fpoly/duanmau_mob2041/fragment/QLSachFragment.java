package hunghhph44272.fpoly.duanmau_mob2041.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import hunghhph44272.fpoly.duanmau_mob2041.DAO.LoaiSachDAO;
import hunghhph44272.fpoly.duanmau_mob2041.DAO.SachDAO;
import hunghhph44272.fpoly.duanmau_mob2041.Model.LoaiSach;
import hunghhph44272.fpoly.duanmau_mob2041.Model.Sach;
import hunghhph44272.fpoly.duanmau_mob2041.R;
import hunghhph44272.fpoly.duanmau_mob2041.adapter.sachAdapter;

public class QLSachFragment extends Fragment {
    SachDAO sachDAO;
    RecyclerView recyclerSach;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlsach, container, false);
        recyclerSach = view.findViewById(R.id.recyclerSach);
        FloatingActionButton floadadd = view.findViewById(R.id.fladd);

        sachDAO = new SachDAO(getContext());

        loadData();

        floadadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaLog();
            }
        });
        return view;

    }

    private void loadData(){

        ArrayList<Sach> list = sachDAO.getDsDauSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSach.setLayoutManager(linearLayoutManager);
        sachAdapter adapter = new sachAdapter(getContext(), list,getDSloaiSach(),sachDAO);
        recyclerSach.setAdapter(adapter);
    }
    private void showDiaLog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsach,null);
        builder.setView(view);

        EditText edttensach = view.findViewById(R.id.edttensach);
        EditText edttien = view.findViewById(R.id.edttien);
        Spinner spnloaisach = view.findViewById(R.id.spnLoaisach);
        EditText edtnamxb = view.findViewById(R.id.edtnamxb);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSloaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1}
        );
        spnloaisach.setAdapter(simpleAdapter);
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tensach = edttensach.getText().toString();
                int tien = Integer.parseInt(edttien.getText().toString());
                HashMap<String,Object> hs = (HashMap<String, Object>) spnloaisach.getSelectedItem();
                int maloai = (int) hs.get("maLoai");
                int namXuatBan = Integer.parseInt(edtnamxb.getText().toString());
                boolean check = sachDAO.themSachMoi(tensach,tien,maloai,namXuatBan);
                if (check){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    //load data
                    loadData();
                }else {
                    Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private ArrayList<HashMap<String, Object>> getDSloaiSach(){
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> list = loaiSachDAO.getDSloaiSach();
        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();

        for (LoaiSach loaiSach : list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("maLoai",loaiSach.getMaLoai());
            hs.put("tenLoai",loaiSach.getTenLoai());

            listHM.add(hs);
        }
        return listHM;
    }
}
