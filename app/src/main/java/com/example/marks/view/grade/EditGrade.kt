package com.example.marks.view.grade

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marks.viewModel.grade.EditGradeViewModel
import com.example.marks.R
import com.example.marks.model.GradeModel
import java.time.LocalDate

class EditGrade : Fragment() {

    companion object {
        fun newInstance() = EditGrade()
}
    private val args: EditGradeArgs by navArgs()

    private lateinit var _viewModel: EditGradeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_grade_fragment, container, false)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel = ViewModelProvider(this).get(EditGradeViewModel::class.java)

        _viewModel.getGrade(args.gradeID).observe(viewLifecycleOwner, Observer { z ->
            view.findViewById<EditText>(R.id.text_interface_edit_grade)?.setText(z.value.toString())
            view.findViewById<EditText>(R.id.text_interface_edit_grade_weight)?.setText(z.weight.toString())
            view.findViewById<EditText>(R.id.text_interface_edit_grade_category)?.setText(z.category)
            view.findViewById<EditText>(R.id.text_interface_edit_grade_comment)?.setText(z.description)
        })

        view.findViewById<Button>(R.id.button_save_edited_grade)?.setOnClickListener{
            val value = view.findViewById<EditText>(R.id.text_interface_edit_grade)?.text.toString().toInt()
            val weight = view.findViewById<EditText>(R.id.text_interface_edit_grade_weight)?.text.toString().toInt()
            val category = view.findViewById<EditText>(R.id.text_interface_edit_grade_category)?.text.toString()
            val description = view.findViewById<EditText>(R.id.text_interface_edit_grade_comment)?.text.toString()
            val grade = GradeModel(args.gradeID, value, weight, category, description,args.studentID,args.subjectID, LocalDate.now().toString())
            _viewModel.update(grade)
            findNavController().popBackStack()
        }



    }
}