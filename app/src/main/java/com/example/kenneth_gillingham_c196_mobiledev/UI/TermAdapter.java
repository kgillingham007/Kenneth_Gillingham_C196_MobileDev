package com.example.kenneth_gillingham_c196_mobiledev.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenneth_gillingham_c196_mobiledev.Entities.TermEntity;
import com.example.kenneth_gillingham_c196_mobiledev.R;

import java.util.List;

/**
 * Project: Kenneth_Gillingham_C196_MobileDev
 * Package: com.example.kenneth_gillingham_c196_mobiledev.UI
 * <p>
 * User: KG19780
 * Date: 2/17/2023
 * <p>
 * Created with Android Studio
 */
public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    private List<TermEntity> mTerms;
    private final Context context;
    private final LayoutInflater mInflator;
    public TermAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context = context;
    }

    public class TermViewHolder extends RecyclerView.ViewHolder{
        private final TextView termListTextView;
        private TermViewHolder(View itemView){
            super(itemView);
            termListTextView = itemView.findViewById(R.id.termTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final TermEntity current = mTerms.get(position);
                    Intent intent = new Intent(context, CourseList.class);
                    intent.putExtra("termID",current.getTermID());
                    intent.putExtra("termTitle", current.getTermTitle());
                    intent.putExtra("termStartDate",current.getTermStartDate());
                    intent.putExtra("termEndDate", current.getTermEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull @Override public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = mInflator.inflate(R.layout.activity_term_list,parent,false);
        return new TermViewHolder((itemView));
    }

    @Override public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position){
        if (mTerms != null){
            final TermEntity current = mTerms.get(position);
            String name = current.getTermTitle();
            holder.termListTextView.setText(name);
        }
        else {
            holder.termListTextView.setText("No Term Title");
        }
    }

    @Override
    public int getItemCount() {
        return mTerms.size();
    }


    public void setTerms(List<TermEntity> terms){
        mTerms = terms;
        notifyDataSetChanged();
    }


}

   