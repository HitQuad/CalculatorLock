package com.example.calculatorlock.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.calculatorlock.R;
import com.example.calculatorlock.databinding.ActivityPasswordSetBinding;
import com.example.calculatorlock.model.PreKey;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordSetActivity extends AppCompatActivity {

    AlertDialog password_info;
    View dialogView;
    AlertDialog.Builder builder;
    Button got_it;

    AlertDialog conform_pass;
    View conform_dialogView;
    AlertDialog.Builder conform_dialog_builder;
    LinearLayout reset , ok;

    ActivityPasswordSetBinding passwordSetBinding;

    SharedPreferences sharedPreferences;

    double num=1 , num1 , num2 , total='\0' , temp=0 ;
    ArrayList<String> number = new ArrayList<>();
    String symbol = null;

    //check only number or not
    String regex = "[0-9]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //setContentView(R.layout.activity_password_set);
        passwordSetBinding = DataBindingUtil.setContentView(this, R.layout.activity_password_set);
        sharedPreferences = getSharedPreferences(PreKey.preference_name,MODE_PRIVATE);

        dialogView = getLayoutInflater().inflate(R.layout.set_password_info_dialog,null);
        builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        got_it = dialogView.findViewById(R.id.got_it);

        password_info = builder.create();
        password_info.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        password_info.setCancelable(false);

        conform_dialogView = getLayoutInflater().inflate(R.layout.confirm_password_dialog,null);
        conform_dialog_builder = new AlertDialog.Builder(this);
        conform_dialog_builder.setView(conform_dialogView);

        reset = conform_dialogView.findViewById(R.id.reset_pass);
        ok = conform_dialogView.findViewById(R.id.conform_pass);
        conform_pass = conform_dialog_builder.create();
        conform_pass.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        conform_pass.setCancelable(false);

        if(!sharedPreferences.getBoolean(PreKey.Password_Set,false))
            password_info.show();

        got_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password_info.dismiss();
            }
        });

        passwordSetBinding.equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(total + "");

                number.clear();
                passwordSetBinding.calculateAnsTextview.setText("");
                passwordSetBinding.numberListTextview.setText(total+"");
                temp = total;

//                if(!sharedPreferences.getBoolean(PreKey.Password_Set,false)) {
//                    if(m.matches())
//                        conform_pass.show();
//                }
//                else {
//                    if (sharedPreferences.getString(PreKey.Password,"1234").equals(total+"")) {
//                        startActivity(new Intent(getApplicationContext(), SecurityQuestionActivity.class));
//                    }
//                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conform_pass.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conform_pass.dismiss();
                sharedPreferences.edit().putBoolean(PreKey.Password_Set,true).apply();
                startActivity(new Intent(getApplicationContext(), SecurityQuestionActivity.class));
            }
        });

        passwordSetBinding.one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;
                calculate("1");
            }
        });

        passwordSetBinding.two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate("2");
                symbol = null;
            }
        });

        passwordSetBinding.three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;
                calculate("3");
            }
        });

        passwordSetBinding.four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;
                calculate("4");
            }
        });

        passwordSetBinding.five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;
                calculate("5");
            }
        });

        passwordSetBinding.six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;
                calculate("6");
            }
        });

        passwordSetBinding.seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;
                calculate("7");
            }
        });

        passwordSetBinding.eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;
                calculate("8");
            }
        });

        passwordSetBinding.nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;
                calculate("9");
            }
        });

        passwordSetBinding.zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;
                calculate("0");
            }
        });

        passwordSetBinding.doubleZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;
                calculate("00");
            }
        });

        passwordSetBinding.dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;
                calculate(".");
            }
        });

        passwordSetBinding.percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = "%" ;
                calculate("%");
            }
        });

        passwordSetBinding.addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = "+" ;
                calculate("+");
            }
        });

        passwordSetBinding.subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = "-" ;
                calculate("-");
            }
        });

        passwordSetBinding.multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = "*" ;
                calculate("*");
            }
        });

        passwordSetBinding.division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = "/" ;
                calculate("/");
            }
        });

        passwordSetBinding.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        passwordSetBinding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.clear();
                passwordSetBinding.numberListTextview.setText("");
                passwordSetBinding.calculateAnsTextview.setText("");
                total = '\0';
                temp = 0;
            }
        });

    }

    private void calculate(String s) {

        int size = number.size();
        String list = null;
        int p = 0;
        if(symbol==null)
        {
            if(size!=0 && num==1) {
                String a = number.get(size-1);
                a+=(s);
                number.set(size-1, a);
            }else if(size!=0 && num==0)
            {
                number.add(s);
                num = 1;
            }
            else {
                number.add(s);
            }
        }
        else
        {
//            if(temp!=0)
//            {
//                number.add(temp+"");
//                temp=0;
//            }

            if(number.size()>0) {
                if (number.get(size - 1).equals("+") || number.get(size - 1).equals("-") ||
                        number.get(size - 1).equals("*") || number.get(size - 1).equals("/")) {
                    number.set(size - 1, s);
                    p=1;
                }
            }

            if(p==0)
                number.add(s);


            num = 0;
        }

        for (int i=0;i<number.size();i++){

            if(i==0) {
                list = (number.get(i));
            }
            else {
                list+=(number.get(i));
            }
        }

        for (int i=0;i<number.size();i++){
            if(i==0)
                num1 = Double.parseDouble(number.get(i));

            if(number.get(i).equals("+")){

                if (i+1<number.size())
                {
                    num2 = Double.parseDouble(number.get(i+1));
                    total = num1 = num1 + num2;
                    passwordSetBinding.calculateAnsTextview.setText(total+"");
                }
            }

            if(number.get(i).equals("-")){

                if (i+1<number.size())
                {
                    num2 = Double.parseDouble(number.get(i+1));
                    total = num1 = num1 - num2;
                    passwordSetBinding.calculateAnsTextview.setText(total+"");
                }
            }

            if(number.get(i).equals("*")){

                if (i+1<number.size())
                {
                    num2 = Double.parseDouble(number.get(i+1));
                    total = num1 = num1 * num2;
                    passwordSetBinding.calculateAnsTextview.setText(total+"");
                }
            }

            if(number.get(i).equals("/")){

                if (i+1<number.size())
                {
                    num2 = Double.parseDouble(number.get(i+1));
                    total = num1 = num1 / num2;
                    passwordSetBinding.calculateAnsTextview.setText(total+"");
                }
            }
        }

        passwordSetBinding.numberListTextview.setText(list);

    }
}