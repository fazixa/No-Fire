package com.nofire.adapter.viewHolders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nofire.Item.Report;
import com.nofire.R;
import com.nofire.ReportDetailActivity;

public class ReportViewHolder extends BaseViewHolder<Report> implements View.OnClickListener {

    private Context mContext;
    private ImageView mReportImage;
    private TextView mReportDescription;
    private Button mReportConfirm;


    public ReportViewHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        itemView.setOnClickListener(this);

        mReportImage = itemView.findViewById(R.id.report_image);
        mReportDescription = itemView.findViewById(R.id.report_description);
        mReportConfirm = itemView.findViewById(R.id.report_confirm);
        mReportConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.report_confirm:
                onConfirmClick();
                break;
                default:
                    onItemClick();
                    break;
        }
    }

    private void onConfirmClick(){
        Toast.makeText(mContext,"Confirmed",Toast.LENGTH_SHORT).show();
    }

    private void onItemClick(){
        Intent intent = new Intent(mContext, ReportDetailActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void bind(Report item) {
        mReportDescription.setText(item.getDescription());
        Glide.with(mContext).load(item.getImage()).into(mReportImage);
    }
}
