package userinputcontols.smile.com.userinputcontrols;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button_flat)
    Button buttonFlat;
    @BindView(R.id.button_raised)
    Button buttonRaised;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.input_layout_password)
    TextInputLayout inputLayoutPassword;
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_layout_name)
    TextInputLayout inputLayoutName;
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_layout_email)
    TextInputLayout inputLayoutEmail;
    @BindView(R.id.btn_signup)
    Button btnSignup;
    @BindView(R.id.textViewPassword)
    TextView textViewPassword;
    @BindView(R.id.textViewPasswordStrength)
    TextView textViewPasswordStrengthIndiactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // button flat is used when we dont want background colur and border.
        // button riased is used when we want background colur as well as border
        // imagebutton is only used when we want to use only icon in button.


    }

    @OnClick(R.id.btn_signup)
    public void onViewClicked() {
        submitForm();
    }

    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
        // Attach TextWatcher to EditText


        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {

            inputLayoutName.setError(getString(R.string.err_msg_name));

            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputPassword.addTextChangedListener(mTextEditorWatcher);
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    // EditTextWacther  Implementation

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
            // When No Password Entered
            textViewPasswordStrengthIndiactor.setText("Not Entered");
        }

        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        public void afterTextChanged(Editable s)
        {
            if(s.length()==0)
                textViewPasswordStrengthIndiactor.setText("Not Entered");
            else if(s.length()<6)
                textViewPasswordStrengthIndiactor.setText("EASY");
            else if(s.length()<10)
                textViewPasswordStrengthIndiactor.setText("MEDIUM");
            else if(s.length()<15)
                textViewPasswordStrengthIndiactor.setText("STRONG");
            else
                textViewPasswordStrengthIndiactor.setText("STRONGEST");

            if(s.length()==20)
                textViewPasswordStrengthIndiactor.setText("Password Max Length Reached");
        }
    };

}
