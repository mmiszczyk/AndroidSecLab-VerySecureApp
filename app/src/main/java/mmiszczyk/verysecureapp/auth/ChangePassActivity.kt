package mmiszczyk.verysecureapp.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mmiszczyk.verysecureapp.R
import kotlinx.android.synthetic.main.activity_change_pass.confirmpass_edit
import kotlinx.android.synthetic.main.activity_change_pass.oldpass_edit
import kotlinx.android.synthetic.main.activity_change_pass.newpass_edit
import kotlinx.android.synthetic.main.activity_change_pass.button

class ChangePassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pass)
        button.setOnClickListener {
            if(confirmpass_edit.text.toString() != newpass_edit.text.toString()){
                Toast.makeText(applicationContext, "New password and confirm password must be the same!",
                               Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            AuthenticationHelper(applicationContext).setPassword(oldpass_edit.text.toString(),
                                                                 newpass_edit.text.toString())
            Thread.sleep(2000)
            if(AuthCommon(applicationContext).getPassword() != newpass_edit.text.toString()){
                Toast.makeText(applicationContext, "Wrong old password!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            Toast.makeText(applicationContext, "Password changed", Toast.LENGTH_SHORT).show()
        }
    }
}