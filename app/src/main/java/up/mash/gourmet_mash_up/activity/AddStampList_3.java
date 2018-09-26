package up.mash.gourmet_mash_up.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import up.mash.gourmet_mash_up.R;
import up.mash.gourmet_mash_up.model.local.Stamp;

public class AddStampList_3 extends AppCompatActivity {

    private ImageView iv_back_button;
    private TextView tv_question_no;
    private TextView tv_ask_question;
    private EditText et_contents_stamp_wishList;
    private Button enter_next;
    private View select_containar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_good_bad_stamp);

        init_common_stamp_wishlist();

        tv_question_no.setText(R.string.q3);
        tv_ask_question.setText(R.string.how_the_taste);

        et_contents_stamp_wishList.setVisibility(View.GONE);

        select_containar = findViewById(R.id.select_container_view);
        ToggleButton iv_good = select_containar.findViewById(R.id.good);
        ToggleButton iv_bad = select_containar.findViewById(R.id.bad);


        iv_good.setBackgroundResource(R.drawable.not_active_good_stamp);
        iv_bad.setBackgroundResource(R.drawable.not_active_bad_stamp);
        iv_good.setOnClickListener((View v) -> {
            if (iv_good.isChecked()) {
                if (iv_bad.isChecked()) {
                    iv_bad.setChecked(false);
                    iv_bad.setBackgroundResource(R.drawable.not_active_bad_stamp);
                }
                iv_good.setBackgroundResource(R.drawable.good_stamp);
            } else {
                iv_good.setBackgroundResource(R.drawable.not_active_good_stamp);
            }
        });


        iv_bad.setOnClickListener(v -> {
            if (iv_bad.isChecked()) {
                if (iv_good.isChecked()) {
                    iv_good.setChecked(false);
                    iv_good.setBackgroundResource(R.drawable.not_active_good_stamp);
                }
                iv_bad.setBackgroundResource(R.drawable.bad_stamp);
            } else {
                iv_bad.setBackgroundResource(R.drawable.not_active_bad_stamp);
            }
        });

        enter_next.setOnClickListener(v -> {
            if (iv_bad.isChecked())
                Stamp.getIntance().setGood(false);
            if (iv_good.isChecked())
                Stamp.getIntance().setGood(true);

            startActivity(new Intent(AddStampList_3.this, MainActivity.class));
            Stamp.getIntance().clear();
            finish();

        });

        iv_back_button.setOnClickListener(v -> onBackPressed());

    }

    public void init_common_stamp_wishlist() {
        iv_back_button = findViewById(R.id.back_button);
        tv_question_no = findViewById(R.id.tv_question_no);
        tv_ask_question = findViewById(R.id.tv_ask_question);
        et_contents_stamp_wishList = findViewById(R.id.et_contents_stamp_wishList);
        enter_next = findViewById(R.id.enterNext);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
