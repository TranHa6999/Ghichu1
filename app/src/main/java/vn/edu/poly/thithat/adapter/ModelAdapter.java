package vn.edu.poly.thithat.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.thithat.R;
import vn.edu.poly.thithat.database.DatabaseHelper;
import vn.edu.poly.thithat.model.Model;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder> {
    private List<Model> list=new ArrayList<>();
    private DatabaseHelper databaseHelper;
    private Context context;

    public ModelAdapter(List<Model> list, DatabaseHelper databaseHelper, Context context) {
        this.list = list;
        this.databaseHelper = databaseHelper;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_re,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Model model=list.get(position);
        holder.tvID.setText(model.getId());
        holder.tvName.setText(model.getName());
        holder.tvPrice.setText(String.valueOf(model.getPrice()));
        holder.ic_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Bạn có thực sự muốn xóa");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHelper=new DatabaseHelper(context);
                        databaseHelper.deleteFood(model.getId());
                        list.remove(model);
                        notifyDataSetChanged();
                    }
                });
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvID,tvName,tvPrice,ic_xoa;
        public ViewHolder(View itemView) {
            super(itemView);
      tvID=itemView.findViewById(R.id.tvID);
      tvName=itemView.findViewById(R.id.tvName);
      tvPrice=itemView.findViewById(R.id.tvPrice);
      ic_xoa=itemView.findViewById(R.id.ic_xoa);
        }
    }
}
