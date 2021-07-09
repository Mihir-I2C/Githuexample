package com.example.api_call;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.api_call.databinding.ItemDesignBinding;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {
    private ArrayList<data> userlist;

    public  Adapter(ArrayList<data>userlist)
    {
        this.userlist=userlist;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        ItemDesignBinding binding=ItemDesignBinding.inflate(layoutInflater,parent,false);
        return  new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        int id=userlist.get(position).getId();
        String firstname=userlist.get(position).getFirst_name();
        String lastname=userlist.get(position).getLast_name();
        String email=userlist.get(position).getEmail();
        String avtarurl=userlist.get(position).getAvtar_url();

        holder.setData(id,firstname,lastname,email,avtarurl);

    }
    @Override
    public int getItemCount() {
//       return (userlist == null) ? 0 : userlist.size();

        return userlist.size();
    }
    public class ViewHolder extends  RecyclerView.ViewHolder {

        ItemDesignBinding binding;
        public ViewHolder(@NonNull ItemDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(int id, String firstname, String lastname, String email, String avtarurl) {
            binding.id.setText(""+id);
            binding.firstname.setText(firstname);
            binding.lastname.setText(lastname);
            binding.email.setText(email);

            Glide.with(binding.getRoot())
                    .load(avtarurl).diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.imageview1);
        }
    }
}
