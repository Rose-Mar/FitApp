import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mag.database.TrainingDao
import com.example.mag.viewModel.TrainingViewModel

class TrainingViewModelFactory(
    private val application: Application, private val trainingDao: TrainingDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrainingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TrainingViewModel(application, trainingDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
