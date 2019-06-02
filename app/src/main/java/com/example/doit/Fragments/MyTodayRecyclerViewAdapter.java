package com.example.doit.Fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.doit.DBHelper;
import com.example.doit.Fragments.TodayFragment.OnListFragmentInteractionListener;
import com.example.doit.Fragments.dummy.DummyContent;
import com.example.doit.Fragments.dummy.DummyContent.DummyItem;
import com.example.doit.NewTaskActivity;
import com.example.doit.R;
import com.example.doit.VeryMainActivity;

import java.nio.FloatBuffer;
import java.util.List;

import static android.app.PendingIntent.getActivity;
import static com.example.doit.DBHelper.DATABASE_NAME;
import static com.example.doit.DBHelper.KEY_ALLLABS;
import static com.example.doit.DBHelper.KEY_DATE;
import static com.example.doit.DBHelper.KEY_DONELABS;
import static com.example.doit.DBHelper.KEY_ID;
import static com.example.doit.DBHelper.KEY_LESSON;
import static com.example.doit.DBHelper.KEY_TYPE;
import static com.example.doit.DBHelper.TABLE_TODO;
import static com.example.doit.Fragments.dummy.DummyContent.ITEMS;
import static com.example.doit.Fragments.dummy.DummyContent.addItem;
import static com.example.doit.Fragments.dummy.DummyContent.createDummyItem;
import static com.example.doit.Fragments.dummy.DummyDone.ITEMS_DONE;
import static com.example.doit.VeryMainActivity.alllabs;
import static com.example.doit.VeryMainActivity.alllabs_done;
import static com.example.doit.VeryMainActivity.date;
import static com.example.doit.VeryMainActivity.date_done;
import static com.example.doit.VeryMainActivity.donelabs;
import static com.example.doit.VeryMainActivity.donelabs_done;
import static com.example.doit.VeryMainActivity.id_done;
import static com.example.doit.VeryMainActivity.lessons;
import static com.example.doit.VeryMainActivity.id;
import static com.example.doit.VeryMainActivity.lessons_done;


/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTodayRecyclerViewAdapter extends RecyclerView.Adapter<MyTodayRecyclerViewAdapter.ViewHolder> {
    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    static DBHelper dbhelper;


    public MyTodayRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_today, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
        holder.buMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper = new DBHelper(holder.itemView.getContext());
                donelabs.set(position, donelabs.get(position) + 1);
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                // Определите содержимое обновленной строки.
                ContentValues updatedValues = new ContentValues();
                // Назначьте значения для каждой строки.
                updatedValues.put(KEY_LESSON, lessons.get(position));
                updatedValues.put(KEY_ALLLABS, alllabs.get(position));
                updatedValues.put(KEY_DONELABS, donelabs.get(position));
                updatedValues.put(KEY_DATE, date.get(position));
                updatedValues.put(KEY_TYPE, 1);
                String where = KEY_ID + "=" + Integer.toString(id.get(position));
                // Обновите строку с указанным индексом, используя новые значения.
                long newRowId = db.update(TABLE_TODO, updatedValues, where, null);
                ITEMS.clear();
                if (alllabs.get(position) <= donelabs.get(position)) {
                    lessons_done.add(lessons.get(position));
                    alllabs_done.add(alllabs.get(position));
                    donelabs_done.add(donelabs.get(position));
                    date_done.add(date.get(position));
                    id_done.add(id.get(position));
                    lessons.remove(position);
                    alllabs.remove(position);
                    donelabs.remove(position);
                    date.remove(position);
                    id.remove(position);
                }
                for (int i = 1; i <= lessons.size(); i++) {
                    addItem(createDummyItem(i));
                    }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;
        private Button buMinus;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            buMinus = (Button) view.findViewById(R.id.buMinus);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
