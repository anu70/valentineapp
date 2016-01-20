package verkstad.org.in.valentineapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by coder on 1/15/2016.
 */
public class TwoFragment extends android.support.v4.app.Fragment{
    RecyclerView recyclerViewlb;
    ArrayList<String> names_leader_board;
    ArrayList<Integer> ranks_leader_board,counts_red_leader_board,counts_yellow_leader_board;
    public TwoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_two, container, false);
        recyclerViewlb= (RecyclerView) view.findViewById(R.id.recycler_view_leader_board);
        names_leader_board=new ArrayList<String>();
        ranks_leader_board=new ArrayList<Integer>();
        counts_red_leader_board=new ArrayList<Integer>();
        counts_yellow_leader_board=new ArrayList<Integer>();


        

        return view;
    }
}
