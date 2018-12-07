package com.ml.zszabo.segunda.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.ml.zszabo.segunda.Model.Item;
import com.ml.zszabo.segunda.R;
import com.ml.zszabo.segunda.Util.FrescoFailListenerController;

import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> items;
    private Context context;
    private ItemClickListener listener;

    public ItemAdapter(ItemClickListener itemClickListener) {
        this.context = (Context) itemClickListener;
        this.listener = itemClickListener;
    }

    public void addItems(List<Item> items) {
        if (this.items == null) this.items = new ArrayList<>();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.cell_item, viewGroup, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        String url = items.get(i).getImageURL();
        Uri uri = Uri.parse(url);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(itemViewHolder.draweeView.getController())
                .setControllerListener(new FrescoFailListenerController())
                .build();
        itemViewHolder.draweeView.setController(controller);
        itemViewHolder.description.setText(items.get(i).getTitle());
        itemViewHolder.priceTag.setText("$".concat(items.get(i).getPriceTag()));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView description, priceTag;
        SimpleDraweeView draweeView;

        public ItemViewHolder(View view) {
            super(view);
            description = view.findViewById(R.id.item_description);
            priceTag =  view.findViewById(R.id.item_price);
            draweeView = view.findViewById(R.id.item_drawee);
        }
    }

    public interface ItemClickListener{
        void onItemClick(Item item);
    }
}
