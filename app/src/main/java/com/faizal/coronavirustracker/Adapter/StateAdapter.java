/*
 * Copyright 2020 FAIZAL.DEV. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.faizal.coronavirustracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.faizal.coronavirustracker.Model.AllStates;
import com.faizal.coronavirustracker.Model.ListItem;
import com.faizal.coronavirustracker.R;

import java.util.ArrayList;
import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder>  {
     List<AllStates> listStateItems;
     Context context;
    // List<AllStates> listStatesAll;


    public StateAdapter(List<AllStates> listStateItems, Context context) {
        this.listStateItems = listStateItems;
        this.context = context;
       // listStatesAll = new ArrayList<>(listStateItems);
    }

    @NonNull
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_state, parent, false);
        return new StateAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StateAdapter.ViewHolder holder, int position) {

        AllStates stateAdapter = listStateItems.get(position);
        holder.stateName.setText(stateAdapter.getState());
        holder.nriState.setText(Integer.toString(stateAdapter.getNri()));
        holder.casesState.setText(Integer.toString(stateAdapter.getCases()));
        holder.curedState.setText(Integer.toString(stateAdapter.getDeath()));
        holder.deathState.setText(Integer.toString(stateAdapter.getRecover()));
    }

    @Override
    public int getItemCount() {
        return listStateItems.size();
    }



//    @Override
//    public Filter getFilter() {
//        return myfilter;
//    }
//    private Filter myfilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<ListItem> filteredList = new ArrayList<>();
//
//            if(constraint == null || constraint.length() == 0){
//                filteredList.addAll(listStatesAll);
//
//            }else{
//                String filterPattern = constraint.toString().toLowerCase().trim();
//                for(ListItem data: listStatesAll){
//                    if(data.getCountry().toLowerCase().contains(filterPattern)){
//                        filteredList.add(data);
//                    }
//                }
//            }
//
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = filteredList;
//            return filterResults;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//
//            listStatesAll.clear();
//            listStatesAll.addAll((List)results.values);
//            notifyDataSetChanged();
//        }
//    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stateName, casesState, nriState, curedState, deathState;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stateName = itemView.findViewById(R.id.state_name);
            casesState = itemView.findViewById(R.id.casesState);
            nriState = itemView.findViewById(R.id.nriState);
            curedState = itemView.findViewById(R.id.recoverState);
            deathState = itemView.findViewById(R.id.deathState);


        }


    }

    public void updateList(List<AllStates> newList) {
        listStateItems = new ArrayList<>();
        listStateItems.addAll(newList);
        notifyDataSetChanged();


    }



}
