package com.example.podborny_eryk_63264_projekt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

// APLIKACJA LISTA ZAKUPÓW

public class MainActivity extends AppCompatActivity {
    //    DODANIE LISTY
    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    //    DEKLARACJA LISTVIEW
    ListView list_view1;
    ListView list_view2;
    //    DEKLARACJA ADAPTERA
    ArrayAdapter arrayAdapter1;
    ArrayAdapter arrayAdapter2;
    //    DEKLARACJA PRZYCISKU
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //      TWORZENIE ADAPTERÓW
        list_view1 = findViewById(R.id.list_view1);
        arrayAdapter1 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, list1);
        list_view1.setAdapter(arrayAdapter1);
        list_view1.setEnabled(false);

        list_view2 = findViewById(R.id.list_view2);
        arrayAdapter2 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, list2);
        list_view2.setAdapter(arrayAdapter2);
        list_view2.setEnabled(false);

        //      TWORZENIE PRZYCISKU
        button = findViewById(R.id.usun_wszystko);
        button.setEnabled(false);
    }
    //      TWORZENIE MENU OPCJI
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    //      OPCJE MENU PO KLIKNIECIU W MENU OPCJI
    @Override
    public boolean onOptionsItemSelected(MenuItem produkt) {
        switch (produkt.getItemId()) {
            case R.id.dodaj_przycisk:
                Dodaj();
                break;
        }
        return true;
    }
    //      FUNKCJA DODAJACA PRODUKT DO LISTY
    private void Dodaj() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Dodaj produkt");

        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.menu_wysuwane, null, false);

        builder.setView(v);
        final EditText dodaj_nazwa = v.findViewById(R.id.dodaj_nazwa);
        final EditText dodaj_ilosc = v.findViewById(R.id.dodaj_ilosc);
        builder.setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int x) {
                if(!dodaj_nazwa.getText().toString().trim().isEmpty() && !dodaj_ilosc.getText().toString().trim().isEmpty()) {
                    list1.add(dodaj_nazwa.getText().toString().trim());
                    arrayAdapter1.notifyDataSetChanged();

                    list2.add(dodaj_ilosc.getText().toString().trim());
                    arrayAdapter2.notifyDataSetChanged();
                    button.setEnabled(true);
                    Toast toast = Toast.makeText(MainActivity.this, "Pozytywnie dodano produkt!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    Toast toast = Toast.makeText(MainActivity.this, "Error! Nie podano produktu!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
    //      FUNKCJA RESETUJACA LISTE PO KLIKNIECIU W PRZYCISK
    public void resetuj(View v){
        list1.clear();
        list2.clear();
        arrayAdapter1.notifyDataSetChanged();
        arrayAdapter2.notifyDataSetChanged();
        Toast toast = Toast.makeText(MainActivity.this, "Pomyślnie usunięto wszystkie produkty!", Toast.LENGTH_LONG);
        toast.show();
        button.setEnabled(false);
    }
}