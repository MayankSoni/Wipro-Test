package in.mayank.test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
import in.mayank.test.MainActivity;
import in.mayank.test.R;
import in.mayank.test.model.Row;

public class BindAdapter extends RecyclerView.Adapter<BindAdapter.ItemRowHolder> {

    private List<Row> dataList;
    private MainActivity mContext;

    public BindAdapter(MainActivity context, List<Row> dataList) {
        this.dataList = dataList;
        this.mContext = context;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, final int position) {
        if (dataList.size() > 0) {

            Glide.with(mContext).load(dataList.get(position).getImageHref())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemRowHolder.imgLogo);

            itemRowHolder.txtTitle.setText(dataList.get(position).getTitle());
            itemRowHolder.txtDescription.setText(dataList.get(position).getDescription());
        }

//        itemRowHolder.itemImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, " " + dataList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView txtTitle, txtDescription;
        protected ImageView  imgLogo;

        public ItemRowHolder(View view) {
            super(view);
            this.txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            this.txtDescription = (TextView) view.findViewById(R.id.txtDescription);
            this.imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
        }

    }
}
