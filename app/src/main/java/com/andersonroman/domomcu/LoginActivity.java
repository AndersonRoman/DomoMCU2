package com.andersonroman.domomcu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText eUsername, eContrasena;
    Button bIniciar;
    TextView tRegistrese;
    String username="",password="",correo;
    Intent intent;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs=getSharedPreferences("MisPreferencias",MODE_PRIVATE);
        editor=prefs.edit();

        username=prefs.getString("username","noname");
        password=prefs.getString("contrasena","nopass");
        correo=prefs.getString("correo","nocorreo");

        Log.d("login",String.valueOf(prefs.getInt("login",-1)));
        Log.d("username",username);
        Log.d("password",password);
        Log.d("correo",correo);

        if (prefs.getInt("login",-1)==1){
            Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
            intent2.putExtra("username", username);
            intent2.putExtra("correo", correo);
            startActivity(intent2);
            finish();
        }//1 hay alguien logueado, -1 no hay
/*
       Bundle extras=getIntent().getExtras();
        username=extras.getString("username");
        password=extras.getString("contrasena");
        correo=extras.getString("correo");*/

        eUsername=(EditText) findViewById(R.id.eUsuario);
        eContrasena=(EditText ) findViewById(R.id.eContrasena);
        bIniciar=(Button) findViewById(R.id.bLogin);
        tRegistrese=(TextView) findViewById(R.id.tRegistrese);

        tRegistrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(LoginActivity.this,RegistroActivity.class);
                startActivityForResult(intent,1234);
            }
        });

        bIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(eUsername.getText().toString().isEmpty() || eContrasena.getText().toString().isEmpty())) {
                    if(!(username.equals("")||password.equals(""))) {
                        if (eUsername.getText().toString().equals(username) && eContrasena.getText().toString().equals(password)) {

                            editor.putInt("login",1);
                            editor.commit();


                            Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
                            intent2.putExtra("username", username);
                            intent2.putExtra("correo", correo);
                            startActivity(intent2);
                            finish();
                        }else{Toast.makeText(LoginActivity.this,"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();}
                    }else {Toast.makeText(LoginActivity.this,"No está registrado, registrese por favor",Toast.LENGTH_LONG).show();}
                }else{Toast.makeText(LoginActivity.this,"Debe llenar los campos",Toast.LENGTH_SHORT).show();}
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1234 && resultCode==RESULT_OK){

            username=data.getExtras().getString("username");
            password=data.getExtras().getString("contrasena");
            correo=data.getExtras().getString("correo");

            editor.putString("username",username);
            editor.putString("contrasena",password);
            editor.putString("correo",correo);

            Log.d("username",password);
        }
        else{
            if (requestCode==1234 && resultCode==RESULT_CANCELED){
                Toast.makeText(this,"ERROR en registro",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
