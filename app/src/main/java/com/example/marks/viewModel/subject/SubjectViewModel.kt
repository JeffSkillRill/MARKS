package com.example.marks.viewModel.subject

import android.app.Application
import androidx.lifecycle.*
import com.example.marks.model.MyDatabase
import com.example.marks.model.SubjectModel
import com.example.marks.model.repositories.SubjectRepository

class SubjectViewModel(application: Application) : AndroidViewModel(application) {

    private val _subjectRepository: SubjectRepository = SubjectRepository(MyDatabase.getDatabase(application).subjectModelDao())
    private var _subjectID: Int = 1
    private var _subject: LiveData<SubjectModel>

    init {
        _subject = _subjectRepository.findSubjectById(_subjectID)
    }

    fun setCurrentSubject(id :Int){
        _subjectID = id
        _subject = _subjectRepository.findSubjectById(_subjectID)
    }

    fun getSubject(subjectID: Int): LiveData<SubjectModel>{
        _subjectID = subjectID
        _subject = _subjectRepository.findSubjectById(_subjectID)
        return _subject
    }

    val currentSubject: LiveData<SubjectModel>
        get() = _subject
}