package hunghhph44272.fpoly.duanmau_mob2041.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hunghhph44272.fpoly.duanmau_mob2041.DAO.LoaiSachDAO;
import hunghhph44272.fpoly.duanmau_mob2041.Model.LoaiSach;
import hunghhph44272.fpoly.duanmau_mob2041.Model.item_click;
import hunghhph44272.fpoly.duanmau_mob2041.R;

public class loaisachAdapter extends RecyclerView.Adapter<loaisachAdapter.ViewHolder>{

    private Context context;
    private ArrayList<LoaiSach> list;
    private item_click itemClick;

    public loaisachAdapter(Context context, ArrayList<LoaiSach> list,item_click itemClick) {
        this.context = context;
        this.list = list;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_loaisach,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtmaloai.setText("Mã loại: " + String.valueOf(list.get(position).getMaLoai()));
        holder.txttenloai.setText("Tên loại: " +list.get(position).getTenLoai());

        holder.ivdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                int check  = loaiSachDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getMaLoai());
                if (check == 1){
                    //load data
                    list.clear();
                    list = loaiSachDAO.getDSloaiSach();
                    notifyDataSetChanged();
                    
                }else if (check == -1){
                    Toast.makeText(context, "Không thể xóa loại sách này", Toast.LENGTH_SHORT).show();
                }
                else if (check == 0){
                    Toast.makeText(context, "Xóa loại sách không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSach loaiSach = list.get(holder.getAdapterPosition());
                itemClick.onClickLs(loaiSach);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtmaloai,txttenloai;
        ImageView ivdelete,ivEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmaloai = itemView.findViewById(R.id.txtmaloai);
            txttenloai = itemView.findViewById(R.id.txttenloai);
            ivdelete = itemView.findViewById(R.id.ivDelete);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }


}
