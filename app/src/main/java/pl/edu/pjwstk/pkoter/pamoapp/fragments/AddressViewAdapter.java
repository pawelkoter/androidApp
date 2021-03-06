package pl.edu.pjwstk.pkoter.pamoapp.fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import pl.edu.pjwstk.pkoter.pamoapp.R;
import pl.edu.pjwstk.pkoter.pamoapp.domain.Address;

public class AddressViewAdapter extends RecyclerView.Adapter<AddressViewAdapter.AddressViewHolder> {

    private final List<Address> mValues;
    private final AddressListFragment.OnAddressClickListener onAddressClickListener;
    private final AddressListFragment.OnNavigateButtonClickListener onNavigateButtonClickListener;


    public AddressViewAdapter(List<Address> mValues,
                              AddressListFragment.OnAddressClickListener onAddressClickListener,
                              AddressListFragment.OnNavigateButtonClickListener onNavigateButtonClickListener) {
        this.mValues = mValues;
        this.onAddressClickListener = onAddressClickListener;
        this.onNavigateButtonClickListener = onNavigateButtonClickListener;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_view, parent, false);
        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddressViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onAddressClickListener) {
                    onAddressClickListener.onAddressClick(holder.mItem);
                }
            }
        });

        holder.mNavigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onNavigateButtonClickListener) {
                    onNavigateButtonClickListener.onNavigationButtonClicked(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class AddressViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mContentView;
        final ImageButton mNavigateButton;
        Address mItem;

        AddressViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            mNavigateButton = (ImageButton) view.findViewById(R.id.navigate_btn);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}