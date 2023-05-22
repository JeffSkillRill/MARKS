package com.example.marks.viewModel.subject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.marks.model.MyDatabase
import com.example.marks.model.SubjectModel
import com.example.marks.model.repositories.SubjectRepository
import kotlinx.coroutines.launch

class AddSubjectViewModel(application: Application) : AndroidViewModel(application) {

    private val _subjectRepository = SubjectRepository(MyDatabase.getDatabase(application).subjectModelDao())
    private lateinit var _subject: LiveData<SubjectModel>
    private var _subjectID: Int = 1

    fun getSubject(subjectID: Int): LiveData<SubjectModel>{
        _subjectID = subjectID
        _subject = _subjectRepository.findSubjectById(_subjectID)
        return _subject
    }

    fun addSubject(name: String){
        viewModelScope.launch {
            val tmp = SubjectModel(0, name)
            _subjectRepository.add(tmp)
        }
    }
}