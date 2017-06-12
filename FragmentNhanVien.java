package com.example.pctruong.apporderfood.FragmentApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pctruong.apporderfood.Activity.DangKiActivity;
import com.example.pctruong.apporderfood.Adapter_Custom.Adapter_NhanVien;
import com.example.pctruong.apporderfood.DAO.NhanVienDAO;
import com.example.pctruong.apporderfood.DTO.NhanVienDTO;
import com.example.pctruong.apporderfood.Activity.MainActivity;
import com.example.pctruong.apporderfood.R;

import java.util.ArrayList;

/**
 * Created by PVTruong on 01/04/2017.
 */

public class FragmentNhanVien extends Fragment {
    ListView listView;
    NhanVienDAO nhanVienDAO;
    Adapter_NhanVien adapter_nhanVien;
    ArrayList<NhanVienDTO> listNhanVien;
    int vitri;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhanvien, container, false);
        setHasOptionsMenu(true);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Nhân Viên");
        listView = (ListView) view.findViewById(R.id.lv_danhsachnhanvien);
        nhanVienDAO = new NhanVienDAO(getActivity());
        getdata();
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vitri=position;
            }
        });

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    getFragmentManager().popBackStack("FragmentNhanVien", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.item_nhanvien,menu);

    }

    public void getdata(){
        listNhanVien = nhanVienDAO.LayDanhSachNhanVien();
        adapter_nhanVien=new Adapter_NhanVien(getActivity(),listNhanVien);
        listView.setAdapter(adapter_nhanVien);
        adapter_nhanVien.notifyDataSetChanged();
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.it_XoaNV : boolean xoa=nhanVienDAO.XoaNhanVien(listNhanVien.get(vitri).getMANV());
                                if(xoa){
                                    listNhanVien.remove(vitri);
                                    getdata();
                                    Toast.makeText(getActivity(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(getActivity(), "Xóa không thành công", Toast.LENGTH_SHORT).show();
                                }
                break;
            case R.id.it_SuaNV :
                Intent intent = new Intent(getActivity(), DangKiActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",listNhanVien.get(vitri));
                intent.putExtra("data",bundle);
                startActivityForResult(intent,1);
                break;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        getdata();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item = menu.add(1, R.id.item_nhanvien, 1, R.string.themnhanvien);
        item.setIcon(R.drawable.nhanvien);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_nhanvien:
                Intent intent = new Intent(getActivity(), DangKiActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
