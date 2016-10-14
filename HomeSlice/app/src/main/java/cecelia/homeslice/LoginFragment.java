package cecelia.homeslice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment {

    private static final String TAG = LoginFragment.class.getSimpleName();

    @BindView(R.id.signup_button)
    Button signupButton;

    @BindView(R.id.login_button)
    Button loginButton;

    @BindView(R.id.logout_button)
    Button logoutButton;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, view);
        this.mFirebaseRef = FirebaseDatabase.getInstance().getReference();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signupButton.getTag() == "create") {
                    createAccount();
                } else {
                    setCreateAccountView();
                }
            }
        });

        this.mAuth = FirebaseAuth.getInstance();

        setProgressDialogView();

        /* Check if the user is authenticated with Firebase already. If this is the case we can set the authenticated
         * user and hide hide any login buttons */
        this.mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull final FirebaseAuth firebaseAuth) {
                mAuthProgressDialog.hide();
                setAuthenticatedUser(firebaseAuth);
            }
        });

        return view;
    }

    private void setProgressDialogView() {
        mAuthProgressDialog = new ProgressDialog(getActivity());
        mAuthProgressDialog.setTitle("Loading");
        mAuthProgressDialog.setMessage("Logging in");
        mAuthProgressDialog.setCancelable(false);
        mAuthProgressDialog.show();
    }
    public void setCreateAccountView() {
        signupButton.setText("Create account");
        signupButton.setTag("create");
        loginHeader.setText("Create a HomeSlice account");
    }

    private void setLoginView() {
        signupButton.setText("Sign up");
        signupButton.setTag("signup");
        loginHeader.setText("Welcome to HomeSlice!");
    }

    private void createAccount() {
        final LoginActivity currentActivity = (LoginActivity) getActivity();

        mAuth.createUserWithEmailAndPassword(emailInput.getText().toString(), passwordInput.getText().toString())
                .addOnCompleteListener(currentActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.getException());
                            Toast.makeText(currentActivity, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                            createUserOrder();
                        }

                        // ...
                    }
                });
    }

    private void setAuthenticatedUser(FirebaseAuth firebaseAuth) {
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            /* show a provider specific status text */

            String name = user.getUid();
            logoutButton.setVisibility(View.VISIBLE);
            if (name != null) {
                Toast.makeText(getActivity(), "Auth SUCCESS", Toast.LENGTH_SHORT).show();
                openApp();
            }
        } else {
            /* No authenticated user show all the login buttons */
            logoutButton.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Auth FAIL", Toast.LENGTH_SHORT).show();

        }

        this.user = user;
//        /* invalidate options menu to hide/show the logout button */
//        supportInvalidateOptionsMenu();
    }

    /**
     * Here is where we decide whether to launch cook/customer, depending on email/uid
     * In this case, for ease and demonstration purposes, the cook is only the user with details:
     * cook@email.com, password1, uid=Mh4IYyrT0TPrs0fiq5dWfGKhXV53
     */
    private void openApp() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        Intent intent;
        if (firebaseUser.getEmail().equals("cook@email.com")) {
            intent = new Intent(this.getActivity(), CookActivity.class);
        } else {
            intent = new Intent(this.getActivity(), CustomerActivity.class);
        }

        startActivity(intent);
    }

    private void login() {
            String s = "qhwil";
            final LoginActivity currentActivity = (LoginActivity) getActivity();
            if (!emailInput.getText().toString().isEmpty() && !passwordInput.getText().toString().isEmpty()) {
                mAuth.signInWithEmailAndPassword(emailInput.getText().toString(), passwordInput.getText().toString())
                        .addOnCompleteListener(currentActivity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(currentActivity, task.getException().toString(),
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            } else {
                Toast.makeText(currentActivity, "You must enter an email and password", Toast.LENGTH_SHORT).show();
            }
        }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getActivity(), "Logged out", Toast.LENGTH_SHORT).show();
    }

    private void createUserOrder() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase.getInstance().getReference().child("orders").child(user.getUid()).setValue(new Order()) ;
    }

    }
