package cn.dankal.demo.SQLite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.dankal.demo.R;
import java.util.List;

public class SQLiteAdapter extends BaseAdapter {

  private Context mContext;
  private List<DBSQLBean> mDbsqlBeanList;

  public SQLiteAdapter(Context context, List<DBSQLBean> dbsqlBeanList) {
    this.mContext = context;
    this.mDbsqlBeanList = dbsqlBeanList;
  }

  @Override public int getCount() {
    return mDbsqlBeanList.size();
  }

  @Override public Object getItem(int position) {
    return mDbsqlBeanList.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View view, ViewGroup parent) {
    DBSQLBean dbsqlBean = mDbsqlBeanList.get(position);
    if (dbsqlBean == null) {
      return null;
    }
    ViewHolder viewHolder = null;
    if (view != null) {
      viewHolder = (ViewHolder) view.getTag();
    } else {
      view = LayoutInflater.from(mContext).inflate(R.layout.item_sql_list_view, null);
      viewHolder = new ViewHolder();
      viewHolder.tv_id = (TextView) view.findViewById(R.id.txt_id);
      viewHolder.tv_name = (TextView) view.findViewById(R.id.txt_name);
      viewHolder.tv_age = (TextView) view.findViewById(R.id.txt_age);
      viewHolder.tv_gender = (TextView) view.findViewById(R.id.txt_gender);
      viewHolder.tv_city = (TextView) view.findViewById(R.id.txt_city);
      view.setTag(viewHolder);
    }
    viewHolder.tv_id.setText(dbsqlBean.id + "");
    viewHolder.tv_name.setText(dbsqlBean.name);
    viewHolder.tv_age.setText(dbsqlBean.age + "");
    viewHolder.tv_gender.setText(dbsqlBean.gender);
    viewHolder.tv_city.setText(dbsqlBean.city);
    return view;
  }

  @Override
  public boolean isEnabled(int position) {
    return false;
  }

  class ViewHolder {
    TextView tv_id;
    TextView tv_name;
    TextView tv_age;
    TextView tv_gender;
    TextView tv_city;
  }
}
