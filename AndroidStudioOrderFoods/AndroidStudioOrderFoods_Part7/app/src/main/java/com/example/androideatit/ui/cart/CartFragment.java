package com.example.androideatit.ui.cart;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androideatit.Common.Common;
import com.example.androideatit.Database.Database;
import com.example.androideatit.Model.Order;
import com.example.androideatit.Model.Request;
import com.example.androideatit.R;
import com.example.androideatit.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class CartFragment extends Fragment {

    public CartFragment(){}

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    TextView txtViewTotalPrice;
    FButton buttonPlace;

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        // Firebase
        database = FirebaseDatabase.getInstance();
        requests=database.getReference("Requests");

        // Init
        recyclerView = root.findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        txtViewTotalPrice = root.findViewById(R.id.total);
        buttonPlace = root.findViewById(R.id.btnPlaceOrder);

        buttonPlace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                showAlertDialog();

            }
        });

        loadListFood();

        return root;
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("One more step!");
        alertDialog.setMessage("Enter your address: ");

        final EditText editTxtAddress = new EditText(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        editTxtAddress.setLayoutParams(lp);
        // Add edit text to alert dialog
        alertDialog.setView(editTxtAddress);
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Create new Request
                Request request = new Request(
                        Common.currentUser.getPhone(),
                        Common.currentUser.getName(),
                        editTxtAddress.getText().toString(),
                        txtViewTotalPrice.getText().toString(),
                        cart
                );

                // Submit to firebase
                // We will be using System.CurrentMilli to key
                requests.child(String.valueOf(System.currentTimeMillis())).setValue(request);

                // Delete cart
                new Database(getContext()).cleanCart();
                Toast.makeText(getContext(), "Thank you, order has been placed!", Toast.LENGTH_SHORT).show();

                // Refresh fragment
                Fragment fm = getFragmentManager().findFragmentById(R.id.nav_host_fragment);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(fm);
                ft.attach(fm);
                ft.commit();
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.show();
    }

    private void loadListFood() {
        cart = new Database(getContext()).getCart();
        adapter = new CartAdapter(cart,getContext());
        recyclerView.setAdapter(adapter);

        // Calculate total price
        int total = 0;
        for(Order order:cart)
            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQty()));

        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtViewTotalPrice.setText(fmt.format(total));
    }
}
