package es.pue.mundo.hola.holamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView text;
    private EditText mPeso;
    private EditText mAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.calculate);
        mAltura = (EditText) findViewById(R.id.altura);
        mPeso = (EditText) findViewById(R.id.peso);

        text = (TextView) findViewById(R.id.resultado);
        text.setText("No se si estas gordo o no, por favor ingresa el peso y la altura");

        mPeso.setHint("Peso");
        mAltura.setHint("Altura");

        EditText peso = (EditText) findViewById(R.id.peso);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double altura = Double.valueOf(mAltura.getText().toString());
                double peso = Double.valueOf(mPeso.getText().toString());
                text.setText(String.format("El IMC es:  %.2f", calculaIMC(peso, altura)));
            }
        });
    }

    private double calculaIMC(double peso, double altura){
        return peso/(altura*altura);
    }
}
