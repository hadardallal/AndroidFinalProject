package com.example.AndroidProject.animals;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AndroidProject.R;

import java.util.ArrayList;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> {
    private ArrayList<AnimalsData> dataset;
    private Context context;

    public CustomeAdapter(Context context, ArrayList<AnimalsData> dataSet) {
        this.context = context;
        this.dataset = dataSet;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAnimalName;
        TextView textViewAnimalDesc;
        ImageView imageView;
        ImageView imgSms;
        ImageView imgMail;
        Button btnClick;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewAnimalName = itemView.findViewById(R.id.txtViewAnimalName);
            textViewAnimalDesc = itemView.findViewById(R.id.txtViewAnimalDesc);
            imageView = itemView.findViewById(R.id.imageView);
            imgMail= itemView.findViewById(R.id.mailView);
            imgSms= itemView.findViewById(R.id.smsView);
            btnClick =itemView.findViewById(R.id.btnClick);
        }
    }

    @NonNull
    @Override
    public CustomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_desc, parent, false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.MyViewHolder holder, int position) {
        AnimalsData animal = dataset.get(position);
        holder.textViewAnimalName.setText(context.getString(animal.getNameResourceId()));
        holder.textViewAnimalDesc.setText(context.getString(animal.getDescriptionResourceId()));
        holder.imageView.setImageResource(animal.getImageResourceId());

        holder.btnClick.setOnClickListener(v -> {
            if (holder.textViewAnimalDesc.getVisibility() == View.VISIBLE) {
                holder.textViewAnimalDesc.setVisibility(View.GONE);
                holder.imageView.setVisibility(View.VISIBLE);
            } else {
                holder.imageView.setVisibility(View.GONE);
                holder.textViewAnimalDesc.setVisibility(View.VISIBLE);
            }
        });


        holder.imgSms.setOnClickListener(v -> shareViaSms(animal, context));

        holder.imgMail.setOnClickListener(v -> shareViaEmail(animal, context));


    }


    private void shareViaSms(AnimalsData animal, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.ask_Phone));

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_PHONE);
        builder.setView(input);

        builder.setPositiveButton(context.getString(R.string.share), (dialog, which) -> {
            String phoneNumber = input.getText().toString();
            String message = context.getString(R.string.share_name) + " " + context.getString(animal.getNameResourceId()) +
                    "\n" + context.getString(R.string.share_description) + " " + context.getString(animal.getDescriptionResourceId());

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + phoneNumber));
            intent.putExtra("sms_body", message);
            context.startActivity(intent);
        });

        builder.setNegativeButton(context.getString(R.string.share_cancel), (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void shareViaEmail(AnimalsData animal, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.ask_Email));

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        builder.setView(input);

        builder.setPositiveButton(context.getString(R.string.share), (dialog, which) -> {
            String email = input.getText().toString();
            String message = context.getString(R.string.share_name) + " " + context.getString(animal.getNameResourceId()) +
                    "\n" + context.getString(R.string.share_description) + " " + context.getString(animal.getDescriptionResourceId());

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.subject));
            intent.putExtra(Intent.EXTRA_TEXT, message);

            try {
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_send)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        builder.setNegativeButton(context.getString(R.string.share_cancel), (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
