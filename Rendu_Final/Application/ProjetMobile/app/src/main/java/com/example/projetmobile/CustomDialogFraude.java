package com.example.projetmobile;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class CustomDialogFraude extends Dialog {
    private EditText Motif;
    private Button confirmer;
    private  Button annuler;
    public CustomDialogFraude(Activity activity) {
        super(activity);
        setContentView(R.layout.boite_dialog_fraude);
        Motif = (EditText)findViewById(R.id.descriptionfraude);
        confirmer = (Button)findViewById(R.id.Confirmer);
        annuler = (Button)findViewById(R.id.Annuler);
    }

    public EditText getMotif() {
        return Motif;
    }

    public Button getConfirmer() {
        return confirmer;
    }

    public Button getAnnuler() {
        return annuler;
    }

    public void setMotif(EditText motif) {
        Motif = motif;
    }

    public void setConfirmer(Button confirmer) {
        this.confirmer = confirmer;
    }

    public void setAnnuler(Button annuler) {
        this.annuler = annuler;
    }
}
