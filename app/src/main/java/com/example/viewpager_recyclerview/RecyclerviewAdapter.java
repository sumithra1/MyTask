    package com.example.viewpager_recyclerview;

    import android.content.Context;
    import android.content.Intent;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.LinearLayout;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.bumptech.glide.Glide;
    import com.bumptech.glide.request.RequestOptions;
    import com.squareup.picasso.Picasso;

    import java.util.List;

    public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
        LayoutInflater layoutInflater;
        List<HomeValues> homeValue;
        Context context;
        Intent intent;
        DatabaseHelper mydb;

        public RecyclerviewAdapter(List<HomeValues> homeValue,Context context) {
            this.layoutInflater =LayoutInflater.from(context);
            this.homeValue = homeValue;
            this.context=context;

        }

        public RecyclerviewAdapter(Context context, List<HomeValues> homeValue) {
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View  view=layoutInflater.inflate(R.layout.custom_list,parent,false);
            ViewHolder viewHolder=new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.id.setText(homeValue.get(position).getId());
            holder.name.setText(homeValue.get(position).getName());
            Picasso.get().load(homeValue.get(position).getImg()).into(holder.img);

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id=homeValue.get(position).getId();
                    String name=homeValue.get(position).getName();
                    String img = homeValue.get(position).getImg();
                    String text = holder.editText.getText().toString();
                    mydb=new DatabaseHelper(context);
                   boolean inserted=mydb.insertData(homeValue.get(position).getId(),homeValue.get(position).getName(),holder.editText.getText().toString());
                    if(inserted==true){

                        Toast.makeText(context,"Data Inserted",Toast.LENGTH_SHORT).show();

                        //Toast.makeText(getItemViewType(),"Data Inserted",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        // Toast.makeText(getApplicationContext(),"Data not Inserted",Toast.LENGTH_SHORT).show();
                        Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(context , Second_activity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("name",name);
                    intent.putExtra("img",img);
                    intent.putExtra("text",text);
                    context.startActivity(intent);


                    //holder.itemView.getContext();

                }
            });

        }

        @Override
        public int getItemCount() {


            return homeValue.size();
        }

        public class   ViewHolder extends RecyclerView.ViewHolder{
            TextView id,name;
            ImageView img;
            EditText editText;
            Button button;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                id=itemView.findViewById(R.id.json_id);
                name=itemView.findViewById(R.id.txtview);
                img=itemView.findViewById(R.id.imgid);
                editText=itemView.findViewById(R.id.edit_text);
                button=itemView.findViewById(R.id.btn);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = editText.getText().toString();
                      //  Toast.makeText(v.getContext(), "Clicked Laugh Vote"+text, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }