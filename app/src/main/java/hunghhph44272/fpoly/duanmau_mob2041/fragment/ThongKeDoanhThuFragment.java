package hunghhph44272.fpoly.duanmau_mob2041.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import hunghhph44272.fpoly.duanmau_mob2041.DAO.ThongKeDAO;
import hunghhph44272.fpoly.duanmau_mob2041.R;

public class ThongKeDoanhThuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongkedoanhthu,container,false);

        EditText edtStar = view.findViewById(R.id.edtStar);
        EditText edtEnd = view.findViewById(R.id.edtEnd);
        Button btnThongke = view.findViewById(R.id.btnThongke);
        TextView txtKetqua = view.findViewById(R.id.txtKetqua);

        Calendar calendar = Calendar.getInstance();

        edtStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                String ngay = "";
                                String thang = "";
                                if (i2 < 10){
                                    ngay = "0" + i2;
                                }else {
                                    ngay = String.valueOf(i2);
                                }
                                if ((i1 + 1) < 10){
                                    thang = "0" + (i1 + 1);
                                }else {
                                    thang = String.valueOf((i1 + 1));
                                }
                                edtStar.setText(i + "/" + thang + "/" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        edtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                String ngay = "";
                                String thang = "";
                                if (i2 < 10){
                                    ngay = "0" + i2;
                                }else {
                                    ngay = String.valueOf(i2);
                                }
                                if ((i1 + 1) < 10){
                                    thang = "0" + (i1 + 1);
                                }else {
                                    thang = String.valueOf((i1 + 1));
                                }
                                edtEnd.setText(i + "/" + thang + "/" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        btnThongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
                String ngaybatdau = edtStar.getText().toString();
                String ngayketthuc = edtEnd.getText().toString();
                int doanhthu = thongKeDAO.getDoanhThu(ngaybatdau,ngayketthuc);
                txtKetqua.setText(doanhthu + "VND");
            }
        });
        return view;

    }
}
