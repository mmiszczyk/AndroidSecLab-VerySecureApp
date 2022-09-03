package mmiszczyk.verysecureapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_first.button_first
import kotlinx.android.synthetic.main.fragment_first.editTextTextPassword
import androidx.navigation.fragment.findNavController
import mmiszczyk.verysecureapp.auth.AuthenticationHelper
import mmiszczyk.verysecureapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var auth : AuthenticationHelper? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = context?.let { AuthenticationHelper(it) }

        button_first.setOnClickListener(View.OnClickListener {
            if(auth?.isPasswordCorrect(editTextTextPassword.text.toString()) == true){
                context?.let {
                    val i = Intent();
                    i.setClass(it, LoggedInActivity::class.java);
                    startActivity(i)
                }
            }
            else findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}