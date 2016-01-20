package verkstad.org.in.valentineapp;


import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by coder on 1/15/2016.
 */
public class OneFragment extends android.support.v4.app.Fragment {

    Functions functions;
    TextView textView,textView2,textView3;
    EditText editText2;
    String current_user,receiver,message;
    String anonymous="no";
    RadioGroup radioGroup;
    RadioButton radiobutton_red,radiobutton_yellow;
    Button send_flowers,received_flowers,send;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> arrayAdapter;
    LinearLayout linearLayout,linearLayout2;
    RecyclerView recyclerView;
    ArrayList<String> sender_name,sent_message;
    ArrayList<Integer> profile_pic;
    CheckBox anonymous_checkbox;
    String red_rose,yellow_rose;


    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_one, container, false);

        functions = new Functions();
        radioGroup = (RadioGroup)view.findViewById(R.id.radio);
        autoCompleteTextView =(AutoCompleteTextView)view.findViewById(R.id.autoCompleteTextView);
        editText2= (EditText)view.findViewById(R.id.message_box);
        linearLayout = (LinearLayout)view.findViewById(R.id.linear);
        linearLayout2 = (LinearLayout)view.findViewById(R.id.linear2);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycler_view);
        textView= (TextView)view.findViewById(R.id.current_user);
        textView2= (TextView)view.findViewById(R.id.display_red);
        textView3= (TextView)view.findViewById(R.id.display_yellow);
        send_flowers= (Button) view.findViewById(R.id.send_flower);
        received_flowers= (Button) view.findViewById(R.id.received_flowers);
        send = (Button) view.findViewById(R.id.send);
        anonymous_checkbox= (CheckBox) view.findViewById(R.id.anonymous);
        radiobutton_red= (RadioButton) view.findViewById(R.id.radiobutton_red);
        radiobutton_yellow= (RadioButton) view.findViewById(R.id.radiobutton_yellow);
        sender_name = new ArrayList<String>();
        sent_message=new ArrayList<String>();
        profile_pic=new ArrayList<Integer>();



        Profile profile = Profile.getCurrentProfile();
        current_user = profile.getName();

         textView.setText(current_user);
        functions.count_roses(OneFragment.this.getActivity(), current_user, new Functions.VolleyCallback() {
            @Override
            public void onSuccess(int size_json) {
                for (int i = 0; i < size_json; i++) {
                    if (functions.receiver_json[i].equals(current_user)) {
                        sender_name.add(functions.sender_json[i]);
                        sent_message.add(functions.message_json[i]);
                        profile_pic.add(R.mipmap.ic_launcher);
                    }
                }
            }
        });


        functions.get_users(OneFragment.this.getActivity(), new Functions.VolleyCallback() {
            @Override
            public void onSuccess(int size) {
                 arrayAdapter = new ArrayAdapter<String>(OneFragment.this.getActivity(), R.layout.support_simple_spinner_dropdown_item, functions.users);
                autoCompleteTextView.setAdapter(arrayAdapter);
                    }
                });

                  autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                receiver = arrayAdapter.getItem(position).toString();

                }
                });
                 autoCompleteTextView.addTextChangedListener(new TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                receiver = null;

                }

                @Override public void afterTextChanged(Editable s) {

                }
                });


                 send_flowers.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);

                }
                });

                 received_flowers.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);
                recyclerView.setLayoutManager(new LinearLayoutManager(OneFragment.this.getActivity()));
                RecyclerViewAdapter adapter=new RecyclerViewAdapter(OneFragment.this.getActivity(),sender_name,sent_message,profile_pic);
                recyclerView.setAdapter(adapter);



                }
                });

        radiobutton_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                switch (v.getId()) {
                    case R.id.radiobutton_red:
                        if (checked) {
                            red_rose = "1";
                            yellow_rose = "0";
                        }
                        break;
                    case R.id.radiobutton_yellow:
                        if (checked) {
                            red_rose = "0";
                            yellow_rose = "1";
                                    break;
                                }
                        }
                    }
                });

        radiobutton_yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton)v).isChecked();
                switch(v.getId()){
                    case R.id.radiobutton_red:
                        if(checked){
                            red_rose="1";
                            yellow_rose="0";
                        }
                        break;
                    case R.id.radiobutton_yellow:
                        if(checked){
                            red_rose="0";
                            yellow_rose="1";
                            break;
                        }
                }

            }
        });

        anonymous_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check=((CheckBox)v).isChecked();
                if(check){
                    anonymous="yes";
                }
                else{anonymous="no";}
            }
        });




        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message=editText2.getText().toString();

                if(receiver==null){
                    Toast.makeText(OneFragment.this.getActivity(),"enter valid name",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (radioGroup.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(OneFragment.this.getActivity(), "Please Select a rose", Toast.LENGTH_SHORT).show();
                    } else {
                        functions.send(OneFragment.this.getActivity(), current_user, receiver,red_rose, yellow_rose, anonymous, message);
                    }

                    radioGroup.clearCheck();
                }
                autoCompleteTextView.setText("");
                editText2.setText("");

            }
        });


        // Inflate the layout for this fragment
        return view;
    }








}
