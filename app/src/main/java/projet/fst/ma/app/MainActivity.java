package projet.fst.ma.app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import projet.fst.ma.app.classes.Etudiant;
import projet.fst.ma.app.service.EtudiantService;

public class MainActivity extends AppCompatActivity {

    private EtudiantService service;

    private EditText etNom, etPrenom, etId;
    private Button   btnAjouter, btnChercher, btnSupprimer;
    private TextView tvResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service      = new EtudiantService(this);

        etNom        = findViewById(R.id.etNom);
        etPrenom     = findViewById(R.id.etPrenom);
        etId         = findViewById(R.id.etId);
        btnAjouter   = findViewById(R.id.btnAjouter);
        btnChercher  = findViewById(R.id.btnChercher);
        btnSupprimer = findViewById(R.id.btnSupprimer);
        tvResultat   = findViewById(R.id.tvResultat);

        // --- Ajouter ---
        btnAjouter.setOnClickListener(v -> {
            String nom    = etNom.getText().toString().trim();
            String prenom = etPrenom.getText().toString().trim();

            if (nom.isEmpty() || prenom.isEmpty()) {
                Toast.makeText(this, "Remplir nom et prénom", Toast.LENGTH_SHORT).show();
                return;
            }

            service.create(new Etudiant(nom, prenom));
            etNom.setText("");
            etPrenom.setText("");
            Toast.makeText(this, "Étudiant ajouté ✓", Toast.LENGTH_SHORT).show();

            // Log de vérification
            for (Etudiant e : service.findAll()) {
                Log.d("ETUDIANT", e.toString());
            }
        });

        // --- Chercher ---
        btnChercher.setOnClickListener(v -> {
            String txt = etId.getText().toString().trim();
            if (txt.isEmpty()) {
                Toast.makeText(this, "Saisir un ID", Toast.LENGTH_SHORT).show();
                return;
            }

            Etudiant found = service.findById(Integer.parseInt(txt));
            if (found == null) {
                tvResultat.setText("");
                Toast.makeText(this, "Aucun étudiant trouvé", Toast.LENGTH_SHORT).show();
            } else {
                tvResultat.setText(found.getNom() + "  " + found.getPrenom());
            }
        });

        // --- Supprimer ---
        btnSupprimer.setOnClickListener(v -> {
            String txt = etId.getText().toString().trim();
            if (txt.isEmpty()) {
                Toast.makeText(this, "Saisir un ID", Toast.LENGTH_SHORT).show();
                return;
            }

            Etudiant toDelete = service.findById(Integer.parseInt(txt));
            if (toDelete == null) {
                Toast.makeText(this, "ID introuvable", Toast.LENGTH_SHORT).show();
                return;
            }

            service.delete(toDelete);
            tvResultat.setText("");
            etId.setText("");
            Toast.makeText(this, "Étudiant supprimé ✓", Toast.LENGTH_SHORT).show();
        });
    }
}