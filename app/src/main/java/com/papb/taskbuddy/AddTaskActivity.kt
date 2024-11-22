import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.papb.taskbuddy.R
import com.papb.taskbuddy.TaskViewModel
import com.papb.taskbuddy.databinding.ActivityAddTaskBinding


class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate binding
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup save button click listener
        setupSaveButton()
    }

    private fun setupSaveButton() {
        binding.saveButton.setOnClickListener {
            val title = binding.taskTitleInput.text.toString().trim()
            val description = binding.taskDescriptionInput.text.toString().trim()

            when {
                title.isBlank() -> {
                    showToast("Title cannot be empty!")
                }
                else -> {
                    val newTask = Task(
                        title = title,
                        description = description
                    )

                    // Add task
                    taskViewModel.insertTask(newTask)

                    // Show success message
                    showToast("Task added!")

                    // Close activity
                    finish()
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

//    private val taskViewModel: TaskViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_task)
//
//        val saveButton: Button = findViewById(R.id.saveButton)
//        val taskTitleInput: TextView = findViewById(R.id.taskTitleInput)
//        val taskDescriptionInput: TextView = findViewById(R.id.taskDescriptionInput)
//
//        saveButton.setOnClickListener {
//            val title = taskTitleInput.text.toString()
//            val description = taskDescriptionInput.text.toString()
//
//            if (title.isNotBlank()) {
//                val newTask = Task(title = title, description = description)
//                taskViewModel.insertTask(newTask)
//                Toast.makeText(this, "Task added!", Toast.LENGTH_SHORT).show()
//                finish()
//            } else {
//                Toast.makeText(this, "Title cannot be empty!", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}
