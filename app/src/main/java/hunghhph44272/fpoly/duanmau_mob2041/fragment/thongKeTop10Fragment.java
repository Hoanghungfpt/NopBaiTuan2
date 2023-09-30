package hunghhph44272.fpoly.duanmau_mob2041.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hunghhph44272.fpoly.duanmau_mob2041.DAO.ThongKeDAO;
import hunghhph44272.fpoly.duanmau_mob2041.Model.Sach;
import hunghhph44272.fpoly.duanmau_mob2041.R;
import hunghhph44272.fpoly.duanmau_mob2041.adapter.top10adapter;

public class thongKeTop10Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongketop10,container,false);

        RecyclerView recyclerTop10 = view.findViewById(R.id.recyclerTop10);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
        ArrayList<Sach> list = thongKeDAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerTop10.setLayoutManager(linearLayoutManager);

        top10adapter adapter = new top10adapter(getContext(),list);
        recyclerTop10.setAdapter(adapter);
        return view;
    }
}
