package up.mash.gourmet_mash_up.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import up.mash.gourmet_mash_up.R;
import up.mash.gourmet_mash_up.data.remote.BaseNetworkRequestModule;
import up.mash.gourmet_mash_up.data.remote.model.login.LoginRes;

import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;

/**
 * Created by derba on 2018-08-24.
 */

public class SignInActivity extends AppCompatActivity {

    TextView mainText;
    TextView subText;
    TextView subText2;
    EditText inputText;
    EditText inputText2;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        mainText = findViewById(R.id.main_text);
        mainText.setVisibility(View.GONE);

        subText = findViewById(R.id.tv1);
        subText.setText(R.string.id);

        subText2 = findViewById(R.id.tv2);
        subText2.setText(R.string.password);

        inputText = findViewById(R.id.ed1);
        inputText2 = findViewById(R.id.ed2);
        inputText2.setInputType(TYPE_TEXT_VARIATION_PASSWORD);
        inputText2.setTransformationMethod(PasswordTransformationMethod.getInstance());

        button = findViewById(R.id.enterNext);
        button.setText(R.string.Log_in);
        button.setOnClickListener(v -> {

            String id = inputText.getText().toString();
            String password = inputText.getText().toString();

            if (id.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            BaseNetworkRequestModule.requestLogIn(id, password, new Callback<LoginRes>() {
                @Override
                public void onResponse(@NonNull Call<LoginRes> call, @NonNull Response<LoginRes> response) {
                    if (response.isSuccessful()) {
                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<LoginRes> call, @NonNull Throwable t) {

                }
            });
        });
    }
}