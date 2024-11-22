import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.papb.taskbuddy.databinding.ItemTaskBinding
import com.papb.taskbuddy.R

class TaskAdapter(
    private var tasks: List<Task>,
    private val onTaskClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(
        private val binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.apply {
                taskTitle.text = task.title
                taskDescription.text = task.description

                // Handle click events
                root.setOnClickListener { onTaskClick(task) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    // Method to update tasks
    fun updateTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}

//class TaskAdapter(private val tasks: List<Task>, private val onTaskChecked: (Task) -> Unit) :
//    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
//
//    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val title: TextView = view.findViewById(R.id.taskTitle)
//        val description: TextView = view.findViewById(R.id.taskDescription)
//        val checkBox: CheckBox = view.findViewById(R.id.taskCheckBox)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_task, parent, false)
//        return TaskViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
//        val task = tasks[position]
//        holder.title.text = task.title
//        holder.description.text = task.description
//        holder.checkBox.isChecked = task.isCompleted
//
//        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
//            onTaskChecked(task.copy(isCompleted = isChecked))
//        }
//    }
//
//    override fun getItemCount(): Int = tasks.size
//}
