package cecelia.homeslice;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cecelia on 10/9/16.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    Order currentOrder;

//    final LoginActivity activity = (LoginActivity) getActivity();

//    @BindView(R.id.signup_button)
//    Button signupButton;

    @BindView(R.id.login_button)
    Button loginButton;

    @BindView(R.id.password_input)
    EditText passwordInput;

    @BindView(R.id.email_input)
    EditText emailInput;

    @BindView(R.id.login_header)
    TextView loginHeader;

    @BindView(R.id.user_info)
    TextView userInfo;

    /* A dialog that is presented until the Firebase authentication finished. */
    private ProgressDialog mAuthProgressDialog;

    /* A reference to the Firebase */
    private DatabaseReference mFirebaseRef;

    /* Data from the authenticated user */
    private FirebaseUser user;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        View view = getLayoutInflater().inflate(R.layout.login_fragment, null);
//        ButterKnife.bind(this, view);
//        FirebaseApp.initializeApp(getApplicationContext());
//
//        Button signupButton = (Button) view.findViewById(R.id.signup_button);
//        setOnClickListeners(signupButton);
//
//        this.mAuth = FirebaseAuth.getInstance();
//
//        setProgressDialogView();
//
//        /* Check if the user is authenticated with Firebase already. If this is the case we can set the authenticated
//         * user and hide hide any login buttons */
//        this.mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull final FirebaseAuth firebaseAuth) {
//                mAuthProgressDialog.hide();
//                setAuthenticatedUser(firebaseAuth);
//            }
//        });



        addMainPageFragment();
    }

    private void setOnClickListeners(Button signupButton) {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag() == "create") {
                    createAccount();
                } else {
                    setCreateAccountView();
                }
            }
        });
    }

    private void setProgressDialogView() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading");
        mAuthProgressDialog.setMessage("Logging in");
        mAuthProgressDialog.setCancelable(false);
        mAuthProgressDialog.show();
    }
    public void setCreateAccountView() {
//        signupButton.setText("Create account");
//        signupButton.setTag("create");
        loginHeader.setText("Create a HomeSlice account");
    }

    private void setLoginView() {
//        signupButton.setText("Sign up");
//        signupButton.setTag("signup");
        loginHeader.setText("Welcome to HomeSlice!");
    }

    private void createAccount() {

        mAuth.createUserWithEmailAndPassword(emailInput.getText().toString(), passwordInput.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void setAuthenticatedUser(FirebaseAuth firebaseAuth) {
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            /* Hide all the login buttons */
            loginButton.setVisibility(View.GONE);
            userInfo.setVisibility(View.VISIBLE);
            /* show a provider specific status text */

            String name = user.getUid();

            if (name != null) {
                loginButton.setVisibility(View.GONE);
                emailInput.setVisibility(View.GONE);
                passwordInput.setVisibility(View.GONE);
                userInfo.setText("Logged in as " + name);
            }
        } else {
            /* No authenticated user show all the login buttons */
            loginButton.setVisibility(View.VISIBLE);
            emailInput.setVisibility(View.VISIBLE);
            passwordInput.setVisibility(View.VISIBLE);
            userInfo.setVisibility(View.GONE);
        }

        this.user = user;
//        /* invalidate options menu to hide/show the logout button */
//        supportInvalidateOptionsMenu();
    }
    private void login() {

        mAuth.signInWithEmailAndPassword(emailInput.getText().toString(), passwordInput.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(getApplicationContext(), R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }




    public void addMainPageFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Add the MainActivityFragment as the starting Fragment when the app is opened
        fragmentTransaction.replace(R.id.fragment_holder, new LoginFragment(), "CURRENT_FRAGMENT");
        fragmentTransaction.commit();
    }


//    private void createAccount() {
//
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d("LoginActivity", "createUserWithEmail:onComplete:" + task.isSuccessful());
//
//                        // If sign in fails, display a message to the user. If sign in succeeds
//                        // the auth state listener will be notified and logic to handle the
//                        // signed in user can be handled in the listener.
//                        if (!task.isSuccessful()) {
//                            Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                        // ...
//                    }
//                });
//
//    }

}
