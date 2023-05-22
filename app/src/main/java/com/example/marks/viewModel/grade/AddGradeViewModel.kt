package com.example.marks.viewModel.grade

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.marks.model.GradeModel
import com.example.marks.model.MyDatabase
import com.example.marks.model.repositories.GradeRepository
import kotlinx.coroutines.launch

class AddGradeViewModel(application: Application) : AndroidViewModel(application) {

    val gradeRepository: GradeRepository


    init{
        gradeRepository = GradeRepository(MyDatabase.getDatabase(application).gradeModelDao())
    }

    fun addGrade(grade : GradeModel){
    viewModelScope.launch{
            gradeRepository.add(grade)
        }
    }
}