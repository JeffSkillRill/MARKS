package com.example.marks.viewModel.grade

import android.app.Application
import androidx.lifecycle.*
import com.example.marks.model.GradeModel
import com.example.marks.model.MyDatabase
import com.example.marks.model.repositories.GradeRepository
import kotlinx.coroutines.launch

class GradeViewModel(application: Application) : AndroidViewModel(application) {
    private var _gradeRepository: GradeRepository
    private lateinit var _grade: LiveData<GradeModel>

    init {
        _gradeRepository =  GradeRepository(MyDatabase.getDatabase(application).gradeModelDao())
    }

    fun getGrade(gradeID: Int): LiveData<GradeModel> {
        _grade = _gradeRepository.findGradeById(gradeID)
        return _grade
    }

    fun deleteGrade() {
        viewModelScope.launch {
            _gradeRepository.delete(_grade.value!!)
        }
    }

}