package randomcompany.trackyourway;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Evan on 18/04/2016.
 */
public class SearchResListAdapter extends BaseAdapter {
    private Context mContext;
    private List<SearchResListView> mSearchResList;

    public SearchResListAdapter(Context mContext, List<SearchResListView> mSearchResList) {
        this.mContext = mContext;
        this.mSearchResList = mSearchResList;
    }

    @Override
    public int getCount() {
        return mSearchResList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSearchResList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext,R.layout.item_search_res_view, null);
        TextView colName = (TextView)v.findViewById(R.id.item_searchRes_colName);
        colName.setText(mSearchResList.get(position).getCollegeName());

        v.setTag(mSearchResList.get(position).getId()+" "+mSearchResList.get(position).getCollegeName());
        return v;
    }
}
