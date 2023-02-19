package com.example.kenneth_gillingham_c196_mobiledev.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenneth_gillingham_c196_mobiledev.Entities.CourseEntity;
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
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<CourseEntity> mCourses;
    private final Context context;
    private final LayoutInflater mInflator;
    public CourseAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context=context;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{
        private final TextView courseTextView;
        private final TextView courseListItemView1;
        private final TextView courseListItemView2;

        private CourseViewHolder(View itemView){
            super(itemView);
            courseTextView = itemView.findViewById(R.id.courseTextView);
            courseListItemView1 = itemView.findViewById(R.id.courseListTextView1);
            courseListItemView2 = itemView.findViewById(R.id.courseListTextView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final CourseEntity current = mCourses.get(position);
                    Intent intent = new Intent(context, AssessmentList.class);
                    intent.putExtra("courseID",current.getCourseID());
                    intent.putExtra("courseTitle", current.getCourseTitle());
                    intent.putExtra("startDate", current.getCourseStartDate());
                    intent.putExtra("endDate", current.getCourseEndDate());
                    intent.putExtra("status", current.getCourseStatus());
                    intent.putExtra("instructorName", current.getCourseInstructorName());
                    intent.putExtra("instructorPhone", current.getCourseInstructorPhone());
                    intent.putExtra("instructorEmail", current.getCourseInstructorEmail());
                    intent.putExtra("termID", current.getTermID());
                    context.startActivity(intent);
                }
            });
        }
    }
    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.course_list_item,parent,false);
        return new CourseViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if (mCourses != null){
            final CourseEntity current = mCourses.get(position);
            holder.courseListItemView1.setText(Integer.toString(current.getCourseID()));
            holder.courseListItemView2.setText(current.getCourseTitle());
        }
        else{
            holder.courseListItemView1.setText("No Course ID");
            holder.courseListItemView2.setText("No Course Title");
        }
    }

    public void setCourses(List<CourseEntity> courses){
        mCourses = courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }
}

   