package jp.bizen.android.lib.package_viewer;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

final class PackageViewerAdapter extends RecyclerView.Adapter<PackageViewerAdapter.ClassListViewHolder> {
    @Nullable
    private final Listener mListener;
    @NonNull
    private List<ObjectInfo> mList;

    PackageViewerAdapter(@Nullable Listener listener) {
        mList = Collections.emptyList();
        mListener = listener;
    }

    public void setItems(@NonNull List<ObjectInfo> items) {
        mList = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClassListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClassListViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_package_viewer,
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(ClassListViewHolder holder, int position) {
        final ObjectInfo modelClassObject = mList.get(position);
        holder.viewUpdate(modelClassObject, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) mListener.onItemClick(modelClassObject);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface Listener {
        void onItemClick(@NonNull final ObjectInfo objectInfo);
    }

    static final class ClassListViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mRootView;
        private ImageView mClassTypeImage;
        private TextView mClassTypeName;
        private TextView mClassName;
        private TextView mClassPackageName;

        ClassListViewHolder(View itemView) {
            super(itemView);
            mRootView = itemView.findViewById(R.id.root_view);
            mClassTypeImage = itemView.findViewById(R.id.class_type_image);
            mClassTypeName = itemView.findViewById(R.id.class_type_name);
            mClassName = itemView.findViewById(R.id.class_name);
            mClassPackageName = itemView.findViewById(R.id.package_name);
        }

        void viewUpdate(@NonNull final ObjectInfo classObject, @NonNull final View.OnClickListener listener) {
            mRootView.setOnClickListener(listener);
            mClassTypeImage.setImageResource(getDrawableRes(classObject.getType()));
            mClassTypeName.setText(classObject.getType().getName());
            mClassName.setText(classObject.getName());
            mClassPackageName.setText(classObject.getPackageName());
        }

        @DrawableRes
        private int getDrawableRes(@NonNull final ObjectInfo.Type classType) {
            switch (classType) {
                case DIRECTORY:
                    return R.drawable.ic_type_directory;
                case UNKNOWN:
                    return R.drawable.ic_type_unknown;
                default:
                    return R.drawable.ic_type_object;
            }
        }
    }
}
