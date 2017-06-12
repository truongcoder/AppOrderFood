package com.example.pctruong.apporderfood.FragmentApp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.pctruong.apporderfood.Adapter_Custom.HienThi_BanAn_Adapter;
import com.example.pctruong.apporderfood.DAO.BanAnDAO;
import com.example.pctruong.apporderfood.DTO.BanAnDTO;
import com.example.pctruong.apporderfood.R;
import com.example.pctruong.apporderfood.Activity.ThemBanAn_Activity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HienThiBanAn_Fragment extends Fragment {

    public static  int request_code_them=1;
    GridView gridView_ht_banan;
    ArrayList<BanAnDTO> list_banan;
    BanAnDAO banAnDAO;
    HienThi_BanAn_Adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_hienthi_banan, container, false);
        setHasOptionsMenu(true);
        gridView_ht_banan= (GridView) view.findViewById(R.id.gridview_banan);
        load();
        return view;
    }

    public void load(){
        list_banan=new ArrayList<>();
        banAnDAO=new BanAnDAO(getActivity());
        list_banan=banAnDAO.load_DanhSach_BanAn();
        adapter=new HienThi_BanAn_Adapter(getActivity(),R.layout.custom_ht_banan,list_banan);
        gridView_ht_banan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item=menu.add(1,R.id.item_thembanan,1,R.string.thembanan);
        item.setIcon(R.drawable.thembanan);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_thembanan:
                Intent intent=new Intent(getActivity(), ThemBanAn_Activity.class);
                startActivityForResult(intent,request_code_them);
                break;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==request_code_them){
            if(resultCode==Activity.RESULT_OK){

                Intent intent=data;
                boolean kiemtra=intent.getBooleanExtra("tenbanan",false);
                if(kiemtra){
                    load();
                    Toast.makeText(getActivity(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }

            }

        }
    }
}
