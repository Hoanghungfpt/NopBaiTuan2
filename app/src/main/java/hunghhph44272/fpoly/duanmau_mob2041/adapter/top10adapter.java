package hunghhph44272.fpoly.duanmau_mob2041.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hunghhph44272.fpoly.duanmau_mob2041.Model.Sach;
import hunghhph44272.fpoly.duanmau_mob2041.R;

public class top10adapter extends RecyclerView.Adapter<top10adapter.ViewHolder>{
    private Context context;
    private ArrayList<Sach>list;

    public top10adapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_top10,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtmaSach.setText(String.valueOf(list.get(position).getMaSach()));
        holder.txttenSach.setText(list.get(position).getTenSach());
        holder.txtSoLuongMuon.setText(String.valueOf(list.get(position).getSoLuongDaMuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtmaSach , txttenSach, txtSoLuongMuon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmaSach = itemView.findViewById(R.id.txtMaSach);
            txttenSach = itemView.findViewById(R.id.txtTensach);
            txtSoLuongMuon = itemView.findViewById(R.id.txtSoLuongMuon);
        }
    }
}
