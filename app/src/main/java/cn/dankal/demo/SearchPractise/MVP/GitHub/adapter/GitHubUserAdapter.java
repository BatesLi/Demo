package cn.dankal.demo.SearchPractise.MVP.GitHub.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.demo.R;
import cn.dankal.demo.SearchPractise.MVP.GitHub.HubUser;
import cn.dankal.demo.SearchPractise.MVP.GitHub.MeUser;

import java.util.List;

public class GitHubUserAdapter extends RecyclerView.Adapter<GitHubUserAdapter.GitHubUserViewHolder> {

    public List<HubUser> mDataList;
    public Context mContext;

    public GitHubUserAdapter(Context context, List<HubUser> dataList) {
        this.mContext = context;
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public GitHubUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_git_hub_user, parent, false);
        return new GitHubUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitHubUserViewHolder holder, int position) {
        holder.populate(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    public class GitHubUserViewHolder extends RecyclerView.ViewHolder {
        public @BindView(R.id.txt_git_hub_user_name)
        TextView mTxtGitHubUserName;
        public @BindView(R.id.txt_git_hub_user_follow_number)
        TextView mTxtGitHubUserFollowNumber;

        public GitHubUserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(HubUser user) {
            mTxtGitHubUserFollowNumber.setText(user.getLogin());
            mTxtGitHubUserFollowNumber.setText(user.getFollowing().toString());
        }
    }
}
