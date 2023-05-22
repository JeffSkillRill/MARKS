package com.example.marks.model.repositories


import androidx.lifecycle.LiveData
import com.example.marks.model.PersonModel
import com.example.marks.model.StudentSubjectModel
import com.example.marks.model.StudentSubjectModelDao
import com.example.marks.model.SubjectModel

class StudentSubjectRepository(private val studentSubjectModelDao: StudentSubjectModelDao){

    suspend fun showStudentsFromSubject(
        subjectID: Int) = studentSubjectModelDao.findPeopleFromSubject(subjectID)

    fun findPeopleFromSubject(subjectID: Int): LiveData<List<PersonModel>> = studentSubjectModelDao.findPeopleFromSubject(subjectID)

    fun findSubjectsForStudent(studentId: Int): LiveData<List<SubjectModel>> = studentSubjectModelDao.findSubjectsForPerson(studentId)
    
    fun findPeopleNotFromSubject(subjectID: Int): LiveData<List<PersonModel>> = studentSubjectModelDao.findPeopleNotFromSubject(subjectID)

    fun findRelationsByStudentID(studentID: Int): LiveData<List<StudentSubjectModel>> = studentSubjectModelDao.findRelationsByStudentID(studentID)

    suspend fun add(studentSubjectModel: StudentSubjectModel) {
        studentSubjectModelDao.addStudentToSubject(studentSubjectModel)
    }
    suspend fun delete(studentSubjectModel: StudentSubjectModel)
        = studentSubjectModelDao.removeStudentFromSubject(studentSubjectModel)
}