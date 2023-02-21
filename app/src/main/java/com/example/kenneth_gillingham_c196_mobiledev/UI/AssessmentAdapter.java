package com.example.kenneth_gillingham_c196_mobiledev.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;

import com.example.kenneth_gillingham_c196_mobiledev.Entities.AssessmentEntity;
import com.example.kenneth_gillingham_c196_mobiledev.R;

import org.w3c.dom.Text;

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
public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    private List<AssessmentEntity> mAssessments;
    private final Context context;
    private final LayoutInflater mInflator;
    public AssessmentAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context = context;
    }

    class AssessmentViewHolder extends RecyclerView.ViewHolder{
        private final TextView assessmentTextView1;
        private final TextView assessmentTextView2;

        private AssessmentViewHolder(View itemView){
            super(itemView);
            assessmentTextView1 = itemView.findViewById(R.id.assessmentListTextView1);
            assessmentTextView2 = itemView.findViewById(R.id.assessmentListTextView2);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                final AssessmentEntity current = mAssessments.get(position);
                Intent intent = new Intent(context, AssessmentDetail.class);
                intent.putExtra("assessmentID",current.getAssessmentID());
                intent.putExtra("title",current.getAssessmentTitle());
                intent.putExtra("startDate",current.getAssessmentStartDate());
                intent.putExtra("endDate",current.getAssessmentEndDate());
                intent.putExtra("type",current.getAssessmentType());
                intent.putExtra("courseID",current.getCourseID());
                context.startActivity(intent);
            });
        }
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.assessment_list_item,parent,false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {

        if (mAssessments != null){
            final AssessmentEntity current = mAssessments.get(position);
            holder.assessmentTextView1.setText(Integer.toString(current.getAssessmentID()));
            holder.assessmentTextView2.setText(current.getAssessmentTitle());
        }
        else {
            holder.assessmentTextView1.setText("No Assessment ID");
            holder.assessmentTextView2.setText("No Assessment Title");
        }

    }

    @Override
    public int getItemCount() {
        if (mAssessments != null)
            return mAssessments.size();
        else
            return 0;
    }

    public void setAssessments(List<AssessmentEntity> assessments){
        mAssessments = assessments;
        notifyDataSetChanged();
    }
}

   