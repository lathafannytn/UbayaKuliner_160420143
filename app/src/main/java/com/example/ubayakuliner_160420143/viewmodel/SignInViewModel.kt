import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ubayakuliner_160420143.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class SignInViewModel(private val userViewModel: UserViewModel) : ViewModel() {

    fun signInUser(email: String, password: String) {
        viewModelScope.launch {
            val user = userViewModel.getUserByEmail(email)
            if (user != null && user.password == password) {
                // Proses login berhasil
            } else {
                // Proses login gagal
            }
        }
    }
}

