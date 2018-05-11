package pl.edu.pjwstk.pkoter.pamoapp.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import pl.edu.pjwstk.pkoter.pamoapp.R;

public class DeleteAddressDialogFragment  extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.delete_address_dialog_message)
                .setPositiveButton(R.string.delete_confirmation, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id) {
                        getTargetFragment().onActivityResult(getTargetRequestCode(),
                                Activity.RESULT_OK, getActivity().getIntent());
                    }
                })
                .setNegativeButton(R.string.cancel, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getTargetFragment().onActivityResult(getTargetRequestCode(),
                                        Activity.RESULT_CANCELED, getActivity().getIntent());
                            }
                        });

        return builder.create();
    }
}
