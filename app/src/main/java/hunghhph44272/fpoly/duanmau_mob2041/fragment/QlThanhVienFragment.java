package hunghhph44272.fpoly.duanmau_mob2041.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import hunghhph44272.fpoly.duanmau_mob2041.DAO.ThanhVienDAO;
import hunghhph44272.fpoly.duanmau_mob2041.Model.ThanhVien;
import hunghhph44272.fpoly.duanmau_mob2041.R;
import hunghhph44272.fpoly.duanmau_mob2041.adapter.ThanhVienAdapter;

public class QlThanhVienFragment extends Fragment {
    ThanhVienDAO thanhVienDAO;
    RecyclerView recyclerThanhVien;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlthanhvien,container,false);
        recyclerThanhVien = view.findViewById(R.id.recyclerThanhVien);
        FloatingActionButton floadAdd = view.findViewById(R.id.floadAdd);

        thanhVienDAO = new ThanhVienDAO(getContext());
        loadData();

        floadAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }

    private void loadData(){

        ArrayList<ThanhVien> list = thanhVienDAO.getDSthanhVien();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerThanhVien.setLayoutManager(linearLayoutManager);
        ThanhVienAdapter adapter = new ThanhVienAdapter(getContext(),list,thanhVienDAO);
        recyclerThanhVien.setAdapter(adapter);
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themthanhvien,null);
        builder.setView(view);

        EditText edthoTen = view.findViewById(R.id.edthoTen);
        EditText edtnamSinh = view.findViewById(R.id.edtnamSinh);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String hoTen = edthoTen.getText().toString();
                String namSinh = edtnamSinh.getText().toString();

                boolean check = thanhVienDAO.themThanhVien(hoTen,namSinh);
                if (check){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
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

}
