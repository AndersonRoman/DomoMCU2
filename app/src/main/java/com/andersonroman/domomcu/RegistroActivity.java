package com.andersonroman.domomcu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {


    EditText eRUsername,eRContrasena,eRRContrasena, eRCorreo;
    Button bRegistrar, bCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        eRUsername= (EditText) findViewById(R.id.eRUsername);
        eRContrasena=(EditText)findViewById(R.id.eRContrasena);
        eRRContrasena=(EditText)findViewById(R.id.eRRContrasena);
        eRCorreo=(EditText)findViewById(R.id.eRCorreo);
        bRegistrar=(Button) findViewById(R.id.bRegistrar);
        bCancelar=(Button) findViewById(R.id.bCancelar);

        bRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(RegistroActivity.this,LoginActivity.class);
                if(!(eRUsername.getText().toString().isEmpty()||eRContrasena.getText().toString().isEmpty()||eRRContrasena.getText().toString().isEmpty()||eRCorreo.getText().toString().isEmpty())) {
                    if (eRContrasena.getText().toString().equals(eRRContrasena.getText().toString())) {
                        Intent intent = new Intent();
                        intent.putExtra("username", eRUsername.getText().toString());
                        intent.putExtra("contrasena", eRContrasena.getText().toString());
                        intent.putExtra("correo", eRCorreo.getText().toString());

                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(RegistroActivity.this, "Las contraseñas no coinciden, reviselas por favor", Toast.LENGTH_LONG).show();
                    }
                }else {Toast.makeText(RegistroActivity.this,"Debe llenar todos los campos",Toast.LENGTH_SHORT).show();}
            }
        });

        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent= new Intent(RegistroActivity.this,LoginActivity.class);
                Intent intent=new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });

    }
}